package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsSendDto {

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
   * 置顶状态
   */
  private Boolean topstate;

  /**
   * 热门状态
   */
  private Boolean hotstate;


  /**
   * 帖子图像下载url
   */
  private List<String> imgUrls;

}
