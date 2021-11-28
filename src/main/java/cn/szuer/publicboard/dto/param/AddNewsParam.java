package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewsParam {

    private Integer userid;

    private String newstitle;

    private String content;

    private Integer newstype;

}
