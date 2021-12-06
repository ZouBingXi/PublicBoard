package cn.szuer.publicboard.service.Impl;

import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Autowired
    private NewsConverter newsConverter;

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


}
