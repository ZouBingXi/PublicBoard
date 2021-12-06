package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubjectSendDto {

    /**
     * 话题编号
     */
    private Integer subjectid;

    /**
     * 发布话题人
     */
    private Integer userid;

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
    private Date sendtime;

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
    private String subjecttypename;

    /**
     * 用户匿名状态
     */
    private Boolean anonymousstate;

    /**
     * 置顶状态
     */
    private String topstate;

    /**
     * 热门状态
     */
    private String hotstate;

    /**
     * 审核状态
     */
    private Boolean examinestate;


}
