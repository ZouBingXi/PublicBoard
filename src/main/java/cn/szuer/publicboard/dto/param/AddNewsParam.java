package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewsParam {
    /**
     * 用户账号
     */
    private Integer userid;

    /**
     * 标题
     */
    private String newstitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 帖子类型
     */
    private Integer newstypeid;


}
