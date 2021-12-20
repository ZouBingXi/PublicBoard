package cn.szuer.publicboard.service.Impl;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.SearchParam;
import cn.szuer.publicboard.mapper.NewsImageMapper;
import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;
import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.MinioUtil;
import cn.szuer.publicboard.utils.NewsUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Autowired
    private NewsImageMapper newsImageMapper;

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private NewsUtil newsUtil;

    @Override
    public List<NewsSendDto> getAll() {
        List<NewsInfo> newsInfos = newsInfoMapper.selectAll();
        List<NewsSendDto> newsSendDtos = new ArrayList<>();
        for (NewsInfo newsInfo:newsInfos)
        {
            newsSendDtos.add(newsUtil.NewsInfo2NewsSendDto(newsInfo));
        }
        return newsSendDtos;
    }

    @Override
    public int add(AddNewsParam addNewsParam) {
        //帖子信息
        NewsInfo news = new NewsInfo();
        //发布帖子人信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addNewsParam.getUserid());
        //帖子类型信息
        NewsType newsType = newsTypeMapper.selectByPrimaryKey(addNewsParam.getNewstypeid());

        //判断用户是否被封禁,被封禁返回26
        if (userInfo.getBanstate()==Boolean.TRUE)
            return 21;

        //判断帖子类型状态是否禁用，禁用返回28
        if(newsType.getTypestate()==Boolean.FALSE)
            return 22;

        //设置newsinfo各属性值
        Integer id = null;
        news.setNewsid(id);
        news.setUserid(addNewsParam.getUserid());
        news.setNewstitle(addNewsParam.getNewstitle());
        news.setContent(addNewsParam.getContent());
        news.setNewstypeid(addNewsParam.getNewstypeid());
        news.setSendtime(new Date());
        news.setViewnum(0);
        news.setLikenum(0);
        news.setTopstate(Boolean.FALSE);
        news.setHotstate(Boolean.FALSE);
        news.setExaminestate(Boolean.FALSE);
        news.setAnonymousstate(userInfo.getAnonymousmode());

        //判断是否匿名
        if(userInfo.getAnonymousmode()==Boolean.FALSE)//正常用户状态
        {
            int res = newsInfoMapper.insertSelective(news);        //插入到帖子信息表
            if(res!=0)
                return 11;
            else
                return 29;
        }
        else//匿名用户状态
        {
            int res = newsInfoMapper.insertSelective(news);        //插入到帖子信息表
            if(res!=0)
                return 12;
            else
                return 29;
        }
    }

    @Override
    public PageInfo<NewsSendDto> getByPage(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<NewsInfo> newsInfos = newsInfoMapper.selectAll();
        List<NewsSendDto> newsSendDtos =  newsConverter.NewsInfos2NewsSendDtos(newsInfos);
        PageInfo pageInfo = new PageInfo<>(newsInfos);
        //再进行setList操作
        pageInfo.setList(newsSendDtos);
        return pageInfo;
    }
    
    @Override
    public int addWithImages(List<MultipartFile> multipartFiles, AddNewsParam addNewsParam) {

        //帖子信息
        NewsInfo news = new NewsInfo();
        //通过工具类获得当前登录用户即发帖人的userid
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authenticationUtil.getAuthenticatedId());

        //判断用户是否被封禁,被封禁返回26
        if (userInfo.getBanstate()==Boolean.TRUE)
            return 21;

        //设置newsinfo各属性值
        Integer id = null;
        news.setNewsid(id);
        news.setUserid(userInfo.getUserid());
        news.setNewstitle(addNewsParam.getNewstitle());
        news.setContent(addNewsParam.getContent());
        news.setNewstypeid(addNewsParam.getNewstypeid());
        news.setSendtime(new Date());
        news.setViewnum(0);
        news.setLikenum(0);
        news.setTopstate(Boolean.FALSE);
        news.setHotstate(Boolean.FALSE);
        news.setExaminestate(Boolean.FALSE);
        news.setAnonymousstate(userInfo.getAnonymousmode());


        //将news插入到帖子信息表
        int res = newsInfoMapper.insertSelective(news);       

        //如果插入失败，返回29
        if(res==0)
            return 29;

        if(multipartFiles!=null)
        {
            //上传图片到服务器，并返回所上传图片的返回体
            BaseResponse<List<String>> UploadResponse = minioUtil.uploadFile(multipartFiles, "news");
            //如果上传失败返回9001
            if(UploadResponse.getCode()==9001)
                return 9001;

            //从上传返回体中获得该帖子的所有图片UUID
            List<String> newsImageUUIDs = UploadResponse.getData();
            //获得帖子ID
            Integer newsid = news.getNewsid(); 

            for(String newsImageUUID: newsImageUUIDs)
            {
              //将该帖子ID和图片的UUID插入数据库
              newsImageMapper.insert(new NewsImage(newsImageUUID, newsid));
            }

        }
        

        //判断是否匿名
        if(userInfo.getAnonymousmode()==Boolean.FALSE)//正常用户状态
        {
                return 11;
        }
        else//匿名用户状态
        {         
                return 12; 
        }
    }

    /**
     * 根据关键字查找相关帖子,并根据相应规则排序
     * @param param
     * @return
     */
    @Override
    public PageInfo<NewsSendDto> searchNews(SearchParam param)
    {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());

        //根据类型名称,查找对应类型id
        String typeName=param.getType();
        Integer type=null;
        if (typeName!=null)
        {
            NewsTypeExample typeExample=new NewsTypeExample();
            typeExample.createCriteria().andTypenameEqualTo(typeName);
            List<NewsType> newsType=newsTypeMapper.selectByExample(typeExample);
            if (newsType==null||newsType.size()==0)
                return null;
            type=newsType.get(0).getNewstypeid();
        }

        //排序方式
        String sort=null;
        if (param.getSort()!=null)
        {
            switch (param.getSort())
            {
                case 2:
                {
                    sort="`viewNum` desc";
                    break;
                }
                case 3:
                {
                    sort="`likeNum` desc";
                    break;
                }
                default:
                {
                    sort="`sendTime` desc";
                    break;
                }
            }
        }

        //将关键词根据空格分开,拼接成模糊查询
        String likeQuery=null;
        if (param.getKey()!=null)
        {
            String[] keys=param.getKey().split("\\s+");
            StringBuilder query=new StringBuilder();
            query.append("%");
            for (String s : keys)
                query.append(s).append("%");
            likeQuery=query.toString();
        }

        //条件查询
        NewsInfoExample example=new NewsInfoExample();
        example.setOrderByClause(sort);                 //设置排序
        NewsInfoExample.Criteria criteria1=example.or();  //设置标题模糊查询
        NewsInfoExample.Criteria criteria2=example.or();    //设置帖子内容模糊查询
        if (likeQuery!=null)
        {
            criteria1.andNewstitleLike(likeQuery);
            criteria2.andContentLike(likeQuery);
        }
        if (type!=null)
        {
            criteria1.andNewstypeidEqualTo(type);
            criteria2.andNewstypeidEqualTo(type);
        }

        List<NewsInfo> list=newsInfoMapper.selectByExampleWithBLOBs(example);
        if (list==null||list.size()==0)
            return null;

        //转为dto
        List<NewsSendDto> resList=new ArrayList<>();
        for (NewsInfo newsInfo:list)
        {
            resList.add(newsUtil.NewsInfo2NewsSendDto(newsInfo));
        }

        return new PageInfo<>(resList);
    }

    @Override
    public BaseResponse<NewsSendDto> view(Integer userid,Integer newsid)
    {
        NewsSendDto newsSendDto = new NewsSendDto();
        //获取账号信息
        UserInfo user = userInfoMapper.selectByPrimaryKey(userid);

        //获取帖子信息
        NewsInfo newsInfo = newsInfoMapper.selectByPrimaryKey(newsid);

        //判断账号是否被封禁
        if(user.getBanstate()==Boolean.TRUE)
            return new BaseResponse<>(500,"获取失败！账号处于封禁状态",newsSendDto);

        newsUtil.NewsInfo2NewsSendDto(newsInfo);

        //更新浏览量
        int res = newsInfoMapper.updateViewNum(newsInfo.getNewsid(),newsInfo.getViewnum()+1);


        return new BaseResponse<>(200,"获取成功！",newsSendDto);
    }

    @Override
    public BaseResponse<PageInfo<NewsSendDto>> viewDiffNews(Integer typeid,Integer pageNum,Integer pageSize)
    {
        List<NewsSendDto> newsSendDtos = new ArrayList<>();

        //设置页面属性
        PageHelper.startPage(pageNum, pageSize);

        //查找输入的帖子类型的所有帖子
        NewsInfoExample example = new NewsInfoExample();
        NewsInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNewstypeidEqualTo(typeid);
        List<NewsInfo> newsInfos = newsInfoMapper.selectByExampleWithBLOBs(example);

        //判断这种类型是否有帖子
        if (newsInfos.size()==0)
            return new BaseResponse(500,"该类型包含帖子数为0，获取失败！");

        //将每条帖子转化为newsenddto
        for (NewsInfo newsInfo:newsInfos)
        {
            //将newsSendDto添加进List里
            newsSendDtos.add(newsUtil.NewsInfo2NewsSendDto(newsInfo));
        }

        PageInfo pageInfo = new PageInfo<>(newsInfos);
        pageInfo.setList(newsSendDtos);
        return new BaseResponse(200,"获取成功！",pageInfo);
    }

    @Override
    public BaseResponse<List<TypeSendDto>> getNewsType()
    {
        //获取所有帖子类型
        List<NewsType> tmp = newsTypeMapper.selectAll();
        List<TypeSendDto> typeSendDtos = new ArrayList<>();

        //去除禁用的帖子类型
        for (NewsType newsType :tmp)
        {
            if(newsType.getTypestate()!=Boolean.FALSE)
            {
                TypeSendDto typeSendDto = new TypeSendDto();
                typeSendDto.setTypeid(newsType.getNewstypeid());
                typeSendDto.setTypename(newsType.getTypename());
                typeSendDtos.add(typeSendDto);
            }
        }

        return new BaseResponse<>(200,"获取成功！",typeSendDtos);
    }

   
    @Override
    public PageInfo<NewsSendDto> getMyNews(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        NewsInfoExample newsInfoExample= new NewsInfoExample();
        NewsInfoExample.Criteria criteria = newsInfoExample.createCriteria();
        criteria.andUseridEqualTo(authenticationUtil.getAuthenticatedId());
        List<NewsInfo> newsInfos = newsInfoMapper.selectByExampleWithBLOBs(newsInfoExample);
        List<NewsSendDto> newsSendDtos = newsConverter.NewsInfos2NewsSendDtos(newsInfos);
        PageInfo pageInfo = new PageInfo<>(newsInfos);

        pageInfo.setList(newsSendDtos);
        return pageInfo;
    }
    
}



