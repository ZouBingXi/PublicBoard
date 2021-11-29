package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSubjectParam {

    private Integer userid;

    private String subjecttitle;

    private String content;

    private Integer subjecttype;

}
