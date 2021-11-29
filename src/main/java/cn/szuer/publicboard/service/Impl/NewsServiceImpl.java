package cn.szuer.publicboard.service.Impl;

import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.NewsStateMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Autowired
    private NewsStateMapper newsStateMapper;

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
        NewsInfo news = new NewsInfo();          //帖子信息
        NewsState newsState = new NewsState();    //帖子状态
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addNewsParam.getUserid());
        NewsType newsType = newsTypeMapper.selectByPrimaryKey(addNewsParam.getNewstype());

        //判断用户是否被封禁,被封禁返回21，代表用户封禁，插入失败
        if (userInfo.getBanstate()==1)
            return 21;

        //判断帖子类型状态是否禁用，禁用返回22，代表该类型被禁用，插入失败
        if(newsType.getTypestate()==0)
            return 22;

        //获取帖子表记录数
        Integer newsid = 0;
        List<NewsInfo> newsInfos= newsInfoMapper.selectAll();
        newsid=newsInfos.size();

        //设置newsinfo各属性值
        news.setNewsid(newsid+1);
        news.setUserid(addNewsParam.getUserid());
        news.setNewstype(addNewsParam.getNewstype());
        news.setNewstitle(addNewsParam.getNewstitle());
        news.setContent(addNewsParam.getContent());
        news.setSendtime(new Date());
        news.setViewnum(0);
        news.setLikenum(0);
        //设置newsstate各属性
        newsState.setNewsid(newsid+1);
        Byte state = 0;
        newsState.setTopstate(state);
        newsState.setHotstate(state);
        newsState.setExaminestate(state);


        //判断是否匿名
        if(userInfo.getAnonymousstate()==0)//正常用户状态
        {
            int res1 = newsInfoMapper.insertSelective(news);        //插入到帖子信息表
            int res2 = newsStateMapper.insertSelective(newsState);  //插入到帖子状态表
            if(res1!=0&&res2!=0)
                return 11;
            else
                return 23;
        }
        else//匿名用户状态
        {
            int res1 = newsInfoMapper.insertSelective(news);        //插入到帖子信息表
            int res2 = newsStateMapper.insertSelective(newsState);  //插入到帖子状态表
            if(res1!=0&&res2!=0)
                return 12;
            else
                return 23;
        }
    }

    @Override
    public PageInfo<NewsSendDto> getByPage(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<NewsInfo> newsInfos = newsInfoMapper.selectAll();
        List<NewsSendDto> newsSendDtos =  newsConverter.NewsInfos2NewsSendDtos(newsInfos);
        return new PageInfo<>(newsSendDtos);
    }


}
