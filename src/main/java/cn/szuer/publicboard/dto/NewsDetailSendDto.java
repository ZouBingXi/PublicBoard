package cn.szuer.publicboard.dto;

import cn.szuer.publicboard.dto.param.CommentParam;
import cn.szuer.publicboard.dto.param.ReplyParam;
import cn.szuer.publicboard.model.NewsComment;
import cn.szuer.publicboard.model.NewsReply;
import lombok.Data;

import java.util.List;

@Data
public class NewsDetailSendDto {

  /**
   * 帖子id
   */
  private Integer newsid;

  /**
   * 发帖人账号
   */
  private Integer userid;

  /**
   * 发帖人用户名
   */
  private String username;

  /**
   * 发帖人头像url
   */
  private String headimage;

  /**
   * 标题
   */
  private String newstitle;

  /**
   * 内容
   */
  private String content;

  /**
   * 发布时间
   */
  private String sendtime;

  /**
   * 浏览量
   */
  private Integer viewnum;

  /**
   * 点赞量
   */
  private Integer likenum;

  /**
   * 帖子类型名字
   */
  private String newstypename;

  /**
   * 发帖人匿名状态
   */
  private Boolean anonymousstate;

  /**
   * 帖子图像下载url
   */
  private List<String> imgUrls;

  /**
   * 是否点赞
   */
  private Boolean isLike;

  /**
   * 帖子评论
   */
  private List<CommentParam> comments;

}
