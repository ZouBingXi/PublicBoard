package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentParam {
    /**
     * 帖子id
     */
    private Integer newsid;

    /**
     * 评论内容
     */
    private String content;
}