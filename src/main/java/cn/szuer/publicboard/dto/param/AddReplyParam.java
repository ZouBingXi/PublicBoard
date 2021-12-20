package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReplyParam {
    /**
     * 评论id
     */
    private Integer commentid;

    /**
     * 内容
     */
    private String content;
}