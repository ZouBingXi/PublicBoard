package cn.szuer.publicboard.utils;

import cn.szuer.publicboard.dto.NewsDetailSendDto;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.*;

import cn.szuer.publicboard.mapper.*;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.NewsType;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.service.NewsService;

import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.MinioUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NewsDetailUtil {

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  NewsTypeMapper newsTypeMapper;

  @Autowired
  NewsImageMapper newsImageMapper;

  @Autowired
  NewsReplyMapper newsReplyMapper;

  @Autowired
  NewsCommentMapper newsCommentMapper;

  @Autowired
  MinioUtil minioUtil;



  public NewsDetailSendDto NewsInfo2NewsDetailSendDto(NewsInfo newsInfo)
  {
      NewsDetailSendDto newsDetailSendDto = new NewsDetailSendDto();

      //获取发帖人用户信息
      UserInfo user = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());

      //获取帖子类型信息
      NewsType newsType = newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstypeid());

      //对newsDetailSendDto各属性赋值
      newsDetailSendDto.setNewsid(newsInfo.getNewsid());
      newsDetailSendDto.setUserid(newsInfo.getUserid());
      newsDetailSendDto.setNewstitle(newsInfo.getNewstitle());
      newsDetailSendDto.setContent(newsInfo.getContent());
      newsDetailSendDto.setViewnum(newsInfo.getViewnum());
      newsDetailSendDto.setLikenum(newsInfo.getLikenum());
      newsDetailSendDto.setNewstypename(newsType.getTypename());
      newsDetailSendDto.setAnonymousstate(user.getAnonymousmode());

      // 判断发帖人是否匿名
      // 匿名则对用户名赋值匿名用户，头像使用默认头像
      // 否则对用户名赋值原用户名，头像使用原头像
      try {
          String img;
          if (newsInfo.getAnonymousstate() == Boolean.TRUE) {
              newsDetailSendDto.setUsername("匿名用户");
              img = minioUtil.getDownloadUrl("DefaultAvatar.jpg", "avatar");
          }
          else{
              newsDetailSendDto.setUsername(user.getUsername());
              img = minioUtil.getDownloadUrl(user.getHeadimage(), "avatar");
          }
          newsDetailSendDto.setHeadimage(img);    //赋值用户头像
      }catch (Exception e) {
          e.printStackTrace();
      }

      //将Date类型转换成时间戳
      SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String time = format.format(newsInfo.getSendtime());
      newsDetailSendDto.setSendtime(time);

      //获取帖子图像uuid
      NewsImageExample example = new NewsImageExample();
      NewsImageExample.Criteria criteria = example.createCriteria();
      criteria.andNewsidEqualTo(newsInfo.getNewsid());
      List<NewsImage> imgs = newsImageMapper.selectByExample(example);    //从帖子图像信息表获取图像uuid
      List<String> uuids  = new ArrayList<>();
      for (NewsImage img:imgs)
          uuids.add(img.getImageuuid());

      //从服务器获取下载帖子图像url
      List<String> imgList = new ArrayList<>();
      if(imgs.size()!=0)  //该帖子有图像才向服务器请求下载图片
      {
          try {
              imgList = minioUtil.getDownloadUrls(uuids,"news");
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      //赋值帖子图像下载url
      newsDetailSendDto.setImgUrls(imgList);

      //获取帖子评论
      NewsCommentExample example1 = new NewsCommentExample();
      NewsCommentExample.Criteria criteria1 = example1.createCriteria();
      criteria1.andNewsidEqualTo(newsInfo.getNewsid());
      List<NewsComment> comments = newsCommentMapper.selectByExampleWithBLOBs(example1);

      //将NewsComment转换成CommentParam
      List<CommentParam> commentParams = new ArrayList<>();
      for (NewsComment newsComment:comments)
      {
          CommentParam commentParam = new CommentParam();
          //获取评论人信息
          UserInfo commentUser = userInfoMapper.selectByPrimaryKey(newsComment.getUserid());

          //对commentParam各属性赋值
          commentParam.setCommentid(newsComment.getCommentid());
          commentParam.setUserid(newsComment.getUserid());
          commentParam.setContent(newsComment.getContent());
          commentParam.setShowreply(Boolean.FALSE);

          //如果用户匿名，commnentParam中的用户头像设置为默认头像,用户名为匿名用户,否则，赋值用户原头像
          try{
              String imgUrl;
              if(newsComment.getAnonymousstate()==Boolean.TRUE)    //用户匿名
              {
                  commentParam.setUsername("匿名用户");
                  imgUrl = minioUtil.getDownloadUrl("DefaultAvatar.jpg", "avatar");
              }
              else   //用户正常状态
              {
                  commentParam.setUsername(commentUser.getUsername());
                  imgUrl = minioUtil.getDownloadUrl(commentUser.getHeadimage(), "avatar");
              }commentParam.setHeadImage(imgUrl);
          }catch (Exception e) {
              e.printStackTrace();
          }

          //转换时间格式
          SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String time1 = format1.format(newsComment.getCommenttime());
          commentParam.setCommenttime(time1); //赋值评论时间

          //获取该评论的回复
          NewsReplyExample newsReplyExample = new NewsReplyExample();
          NewsReplyExample.Criteria criteria2 = newsReplyExample.createCriteria();
          criteria2.andCommentidEqualTo(newsComment.getCommentid());
          List<NewsReply> newsReplies = newsReplyMapper.selectByExampleWithBLOBs(newsReplyExample);

          //赋值回复数
          commentParam.setReplynum(newsReplies.size());
          //将newsReply转化成replyparam
          List<ReplyParam> replyParams = new ArrayList<>();
          for (NewsReply newsReply:newsReplies)
          {
              ReplyParam replyParam = new ReplyParam();
              UserInfo replyUser = userInfoMapper.selectByPrimaryKey(newsReply.getUserid());

              //赋值replyParam各属性
              replyParam.setReplyid(newsReply.getReplyid());
              replyParam.setTargetid(newsReply.getTargetid());
              replyParam.setCommentid(newsReply.getCommentid());
              replyParam.setUserid(newsReply.getUserid());
              replyParam.setContent(newsReply.getContent());

              //如果用户匿名，replyParam中的用户名为匿名用户,否则，赋值用户原用户名
              try{
                  String imgUrl;
                  if(newsReply.getAnonymousstate()==Boolean.TRUE)    //用户匿名
                  {
                      imgUrl = minioUtil.getDownloadUrl("DefaultAvatar.jpg", "avatar");
                      replyParam.setUsername("匿名用户");
                  }
                  else   //用户正常状态
                  {
                      imgUrl = minioUtil.getDownloadUrl(userInfoMapper.selectByPrimaryKey(newsReply.getUserid()).getHeadimage(), "avatar");
                      replyParam.setUsername(replyUser.getUsername());
                  }
                  replyParam.setHeadImage(imgUrl);
              }catch (Exception e) {
                  e.printStackTrace();
              }

              //转换时间格式
              SimpleDateFormat format2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              replyParam.setCommenttime(format2.format(newsReply.getCommenttime())); //赋值评论时间

              //将replyParam加入到列表中
              replyParams.add(replyParam);
          }
          //赋值给replys
          commentParam.setReplys(replyParams);

          //将commentParam添加如commentParams列表
          commentParams.add(commentParam);
      }

      newsDetailSendDto.setComments(commentParams);   //赋值newsDetailSendDto的评论列表

      return newsDetailSendDto;
  }

  public List<NewsDetailSendDto> NewsInfos2NewsDetailSendDtos(List<NewsInfo> newsInfos)
  {
      if(newsInfos==null)
          return null;

      List<NewsDetailSendDto> newsDetailSendDtos = new ArrayList<>();
      for(int i = 0; i < newsInfos.size();i++)
          newsDetailSendDtos.add(NewsInfo2NewsDetailSendDto(newsInfos.get(i)));
      return newsDetailSendDtos;
  }


  public NewsSendDto NewsInfo2NewsSendDto(NewsInfo newsInfo)
  {
      NewsSendDto newsSendDto = new NewsSendDto();
      UserInfo user = userInfoMapper.selectByPrimaryKey(newsInfo.getUserid());

      //设置newsSendDto各属性
      newsSendDto.setNewsid(newsInfo.getNewsid());
      newsSendDto.setUserid(newsInfo.getUserid());
      newsSendDto.setNewstitle(newsInfo.getNewstitle());
      newsSendDto.setContent(newsInfo.getContent());
      newsSendDto.setViewnum(newsInfo.getViewnum());
      newsSendDto.setLikenum(newsInfo.getLikenum());
      newsSendDto.setNewstypename(newsTypeMapper.selectByPrimaryKey(newsInfo.getNewstypeid()).getTypename());

      // 判断发帖人是否匿名
      // 匿名则对用户名赋值匿名用户，头像使用默认头像
      // 否则对用户名赋值原用户名，头像使用原头像
      try {
          String img;
          if (user.getAnonymousmode() == Boolean.TRUE) {
              newsSendDto.setUsername("匿名用户");
              img = minioUtil.getDownloadUrl("DefaultAvatar.jpg", "avatar");
          }
          else{
              newsSendDto.setUsername(user.getUsername());
              img = minioUtil.getDownloadUrl(user.getHeadimage(), "avatar");
          }
          newsSendDto.setHeadimage(img);    //赋值用户头像
      }catch (Exception e) {
          e.printStackTrace();
      }

      //将Date类型转换成时间戳
      SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String time = format.format(newsInfo.getSendtime());
      newsSendDto.setSendtime(time);

      //获取帖子图像uuid
      NewsImageExample example = new NewsImageExample();
      NewsImageExample.Criteria criteria = example.createCriteria();
      criteria.andNewsidEqualTo(newsInfo.getNewsid());
      List<NewsImage> imgs = newsImageMapper.selectByExample(example);    //从帖子图像信息表获取图像uuid
      List<String> uuids  = new ArrayList<>();
      for (NewsImage img:imgs)
          uuids.add(img.getImageuuid());

      //从服务器获取下载帖子图像url
      List<String> imgList = new ArrayList<>();
      if(imgs.size()!=0)  //该帖子有图像才向服务器请求下载图片
      {
          try {
              imgList = minioUtil.getDownloadUrls(uuids,"news");
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      //赋值帖子图像下载url
      newsSendDto.setImgUrls(imgList);

      return newsSendDto;
  }


  public List<NewsSendDto> NewsInfos2NewsSendDtos(List<NewsInfo> newsInfos)
  {
      if(newsInfos==null)
          return null;

      List<NewsSendDto> newsSendDtos = new ArrayList<>();
      for(int i = 0; i < newsInfos.size();i++)
          newsSendDtos.add(NewsInfo2NewsSendDto(newsInfos.get(i)));
      return newsSendDtos;
  }

  
}
