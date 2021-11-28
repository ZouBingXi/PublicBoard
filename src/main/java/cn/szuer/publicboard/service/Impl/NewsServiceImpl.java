package cn.szuer.publicboard.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.NewsReceiveDto;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {
    
    @Autowired
    private UserInfoMapper userInfoMapper;
    private NewsInfoMapper newsInfoMapper;
    private NewsTypeMapper newsTypeMapper;
    private NewsStateMapper newsStateMapper;

    @Override
    public List<NewsSendDto> getAll() {
        NewsInfoExample example = new NewsInfoExample();
        List<NewsInfo> newsInfos = newsInfoMapper.selectByExample(example);
        List<NewsSendDto> newsSendDtos = copyList(newsInfos);
        return newsSendDtos;
    }

    @Override
    public int addNews(NewsReceiveDto newsReceiveDto) {
        NewsInfo news = null;          //帖子信息
        NewsState newsState = null;    //帖子状态
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(newsReceiveDto.getUserid());
        NewsType newsType = newsTypeMapper.selectByPrimaryKey(newsReceiveDto.getNewstype());

        //判断用户是否被封禁,被封禁返回21，代表用户封禁，插入失败
        if (userInfo.getBanstate()==0)
            return 21;

        //判断帖子类型状态是否禁用，禁用返回22，代表该类型被禁用，插入失败
        if(newsType.getTypestate()==0)
            return 22;

        //获取帖子表记录数
        Integer newsid = 0;
        NewsStateExample example = new  NewsStateExample();
        NewsStateExample.Criteria criteria = example.createCriteria();
        criteria.andExaminestateBetween((byte)0,(byte)1);
        newsid = newsStateMapper.countByExample(example) + 1;
        //设置newsinfo各属性值
        news.setNewsid(newsid);
        news.setUserid(newsReceiveDto.getUserid());
        news.setNewstype(newsReceiveDto.getNewstype());
        news.setNewstitle(newsReceiveDto.getNewstitle());
        news.setContent(newsReceiveDto.getContent());
        news.setSendtime(new Date());
        news.setViewnum(0);
        news.setLikenum(0);
        //设置newsstate各属性
        newsState.setNewsid(newsid);
        newsState.setTopstate((byte)0);
        newsState.setHotstate((byte)0);
        newsState.setExaminestate((byte)0);

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
        NewsInfoExample example = new NewsInfoExample();
        List<NewsInfo> newsInfos = newsInfoMapper.selectByExample(example);
        List<NewsSendDto> newsSendDtos =  copyList(newsInfos);
        return new PageInfo<>(newsSendDtos);
    }

    private List<NewsSendDto> copyList(List<NewsInfo> records)
    {
        List<NewsSendDto> newsSendDtos = new ArrayList<>();
        for(NewsInfo record : records)
        {
            newsSendDtos.add(copy(record));
        }
        return newsSendDtos;
    }

    private NewsSendDto copy(NewsInfo newsInfo){
        NewsSendDto newsSendDto = new NewsSendDto();

        //使用BeanUtils.copyProperties方法进行NewsInfo和NewsSendDto之间部分属性的赋值
        BeanUtils.copyProperties(newsInfo, newsSendDto);

        //将用户信息表里的用户名取出，并赋值给newsSendDto.username
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());
        newsSendDto.setUsername(userInfo.getUsername());

        //将newInfo里的newstype从类型编号转换成类型名称，并赋值给newsSendDto.newstype
        NewsType newsType = newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstype());
        newsSendDto.setTypename(newsType.getTypename());

        //从帖子状态表取出该帖子的状态信息，并在newsSendDto对应赋值
        NewsState newsState = newsStateMapper.selectByPrimaryKey(newsInfo.getNewsid());
        //赋值置顶状态
        if (newsState.getTopstate()==1)
            newsSendDto.setTopstate("已被置顶");
        else
            newsSendDto.setTopstate("未被置顶");
        //赋值热门状态
        if (newsState.getHotstate()==1)
            newsSendDto.setHotstate("不是热门");
        else
            newsSendDto.setHotstate("是热门");
        //赋值审核状态
        if (newsState.getExaminestate()==1)
            newsSendDto.setExaminestate("审核通过");
        else
            newsSendDto.setExaminestate("审核中");

        //获取图像信息
        List<String> image_path = null;
        image_path.add("E://NewsImage//1//image1.jpg");
        newsSendDto.setImageaddress(image_path);

        return newsSendDto;
    }

}
