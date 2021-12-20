package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParam
{
    /**
     * 评论id
     */
    private Integer commentid;

    /**
     * 用户账号
     */
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 评论时间
     */
    private String commenttime;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否展示回复
     */
    private Boolean showreply;

    /**
     * 回复数
     */
    private Integer replynum;

    /**
     * 回复
     */
    private List<ReplyParam> replys;
}

