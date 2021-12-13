package cn.szuer.publicboard.service.Impl;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;

import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.NewsType;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.NewsImageMapper;
import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;

import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.MinioUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;


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

    @Override
    public List<NewsSendDto> getAll() {
        List<NewsInfo> newsInfos = newsInfoMapper.selectAll();
        List<NewsSendDto> newsSendDtos = newsConverter.NewsInfos2NewsSendDtos(newsInfos);
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

        //获取帖子图像uuid
        NewsImageExample example = new NewsImageExample();
        NewsImageExample.Criteria criteria = example.createCriteria();
        criteria.andNewsidEqualTo(newsInfo.getNewsid());
        List<NewsImage> imgs = newsImageMapper.selectByExample(example);
        List<String> uuids  = new ArrayList<>();
        for (NewsImage img:imgs)
        {
            uuids.add(img.getImageuuid());
        }

        //从服务器获取下载帖子图像url
        List<String> imgList = new ArrayList<>();
        if(imgs.size()!=0)  //该帖子有图像才向服务器请求下载图片
        {
            try {
                imgList = minioUtil.getDownloadUrls(uuids,"news");
            } catch (Exception e) {
                e.printStackTrace();
                return new BaseResponse<>(500,"获取失败！",newsSendDto);
            }
        }

        //获取发帖人信息
        UserInfo sender = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());
        //获取发帖人头像url
        String headimg = null;
        try {
            headimg = minioUtil.getDownloadUrl(sender.getHeadimage(),"avatar");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //设置NewsSendDto各属性
        newsSendDto.setUserid(newsInfo.getUserid());
        newsSendDto.setUsername(sender.getUsername());
        newsSendDto.setHeadimage(headimg);
        newsSendDto.setNewstitle(newsInfo.getNewstitle());
        newsSendDto.setContent(newsInfo.getContent());
        newsSendDto.setViewnum(newsInfo.getViewnum() + 1);
        newsSendDto.setLikenum(newsInfo.getLikenum());
        newsSendDto.setAnonymousstate(newsInfo.getAnonymousstate());
        newsSendDto.setTopstate(newsInfo.getTopstate());
        newsSendDto.setHotstate(newsInfo.getHotstate());
        newsSendDto.setImgUrls(imgList);
        //将Date类型转换成时间戳
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(newsInfo.getSendtime());
        newsSendDto.setSendtime(time);
        //设置帖子类型名字
        String typename = newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstypeid()).getTypename();
        newsSendDto.setNewstypename(typename);

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
        List<NewsInfo> newsInfos = newsInfoMapper.selectByExample(example);

        //判断这种类型是否有帖子
        if (newsInfos.size()==0)
            return new BaseResponse(500,"该类型包含帖子数为0，获取失败！");

        //将每条帖子转化为newsenddto
        for (NewsInfo newsInfo:newsInfos)
        {
            NewsSendDto newsSendDto = new NewsSendDto();

            //获取帖子图像uuid
            NewsImageExample example1 = new NewsImageExample();
            NewsImageExample.Criteria criteria1 = example1.createCriteria();
            criteria.andNewsidEqualTo(newsInfo.getNewsid());
            List<NewsImage> imgs = newsImageMapper.selectByExample(example1);
            List<String> uuids  = new ArrayList<>();
            for (NewsImage img:imgs)
            {
                uuids.add(img.getImageuuid());
            }

            //从服务器获取下载帖子图像url
            List<String> imgList = new ArrayList<>();
            if(imgs.size()!=0)  //该帖子有图像才向服务器请求下载图片
            {
                try {
                    imgList = minioUtil.getDownloadUrls(uuids,"news");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new BaseResponse(500,"获取失败！");
                }
            }

            //获取发帖人信息
            UserInfo sender = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());
            //获取发帖人头像url
            String headimg = null;
            try {
                headimg = minioUtil.getDownloadUrl(sender.getHeadimage(),"avatar");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //设置NewsSendDto各属性
            newsSendDto.setNewsid(newsInfo.getNewsid());
            newsSendDto.setUserid(newsInfo.getUserid());
            newsSendDto.setUsername(sender.getUsername());
            newsSendDto.setHeadimage(headimg);
            newsSendDto.setNewstitle(newsInfo.getNewstitle());
            newsSendDto.setContent(newsInfo.getContent());
            newsSendDto.setViewnum(newsInfo.getViewnum());
            newsSendDto.setLikenum(newsInfo.getLikenum());
            newsSendDto.setAnonymousstate(newsInfo.getAnonymousstate());
            newsSendDto.setTopstate(newsInfo.getTopstate());
            newsSendDto.setHotstate(newsInfo.getHotstate());
            newsSendDto.setImgUrls(imgList);
            //将Date类型转换成时间戳
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(newsInfo.getSendtime());
            newsSendDto.setSendtime(time);
            //设置帖子类型名字
            String typename = newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstypeid()).getTypename();
            newsSendDto.setNewstypename(typename);

            //将newsSendDto添加进List里
            newsSendDtos.add(newsSendDto);
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
}
