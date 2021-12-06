package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSubjectParam {
    /**
     * 用户账号
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
     * 话题类型
     */
    private Integer subjecttypeid;


}
