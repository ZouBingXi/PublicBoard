package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubjectSendDto {

    /**
     * 话题id
     */
    private Integer subjectid;

    /**
     * 发布人账号
     */
    private Integer userid;

    /**
     * 发布人用户名
     */
    private String username;

    /**
     * 发布人头像url
     */
    private String headimage;

    /**
     * 标题
     */
    private String subjecttitle;

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
     * 话题类型名字
     */
    private String subjecttypename;

    /**
     * 发布人匿名状态
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
     * 话题图像下载url
     */
    private List<String> imgUrls;

}
