package cn.szuer.publicboard.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.mapper.NewsImageMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.UserTypeMapper;
import cn.szuer.publicboard.model.NewsImageExample;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.NewsImage;


/**
 * 用于转换帖子的工具类，包括判断该帖是否匿名
 */
@Component
public class NewsUtil {
  

  @Autowired
  NewsImageMapper newsImageMapper;

  @Autowired
  NewsTypeMapper newsTypeMapper;

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  MinioUtil minioUtil;
    

  public NewsSendDto NewsInfo2NewsSendDto(NewsInfo newsInfo)
  {
      NewsSendDto newsSendDto=new NewsSendDto();
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

      //获取帖子图像url
      List<String> imgList = new ArrayList<>();
      if(imgs.size()!=0)  //该帖子有图像才获取图片url
      {
          try {
              imgList = minioUtil.getDownloadUrls(uuids,"news");
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      //获取发帖人信息
      UserInfo sender = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());

      // 判断该帖是否匿名
      if(!newsInfo.getAnonymousstate())
      {
          //获取发帖人头像url
          String headimg = null;
          try {
              headimg = minioUtil.getDownloadUrl(sender.getHeadimage(),"avatar");
          } catch (Exception e) {
              e.printStackTrace();
          }
          newsSendDto.setUsername(sender.getUsername());
          newsSendDto.setHeadimage(headimg);
      } 
      else
      {
          //如果是匿名贴则用户名设置为“匿名用户”,头像设置为默认头像
          newsSendDto.setUsername("匿名用户");
          newsSendDto.setHeadimage(minioUtil.getDefaultAvatar());
      }

      //设置NewsSendDto各属性
      newsSendDto.setNewsid(newsInfo.getNewsid());
      newsSendDto.setUserid(newsInfo.getUserid());
      newsSendDto.setNewstitle(newsInfo.getNewstitle());
      newsSendDto.setContent(newsInfo.getContent());
      newsSendDto.setViewnum(newsInfo.getViewnum() + 1);
      newsSendDto.setLikenum(newsInfo.getLikenum());
      newsSendDto.setAnonymousstate(newsInfo.getAnonymousstate());
      newsSendDto.setTopstate(newsInfo.getTopstate());
      newsSendDto.setHotstate(newsInfo.getHotstate());
      newsSendDto.setImgUrls(imgList);
      //将Date类型转换成时间戳
      SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
      String time = format.format(newsInfo.getSendtime());
      newsSendDto.setSendtime(time);
      //设置帖子类型名字
      String typename = newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstypeid()).getTypename();
      newsSendDto.setNewstypename(typename);
      return newsSendDto;
  }
}
