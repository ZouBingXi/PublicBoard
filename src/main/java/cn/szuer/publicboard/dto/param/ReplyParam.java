package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyParam
{
    /**
     * 回复id
     */
    private Integer replyid;

    /**
     * 目标id
     */
    private Integer targetid;

    /**
     * 评论id
     */
    private Integer commentid;

    /**
     *  用户账号
     */
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 评论时间
     */
    private String commenttime;

    /**
     * 评论内容
     */
    private String content;

}

