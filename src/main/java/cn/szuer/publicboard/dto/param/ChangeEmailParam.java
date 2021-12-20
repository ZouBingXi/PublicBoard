package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailParam {


    /**
     * 新邮箱
     */
    private String  email;
    /**
     * 验证码
     */
     private String code;
  
}
