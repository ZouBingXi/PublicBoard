package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordParam
{
    /**
     * 新密码
     * @mock 123456789
     */
    private String newPassword;
    /**
     * 邮箱
     * @mock 123456789@qq.com
     */
    private String email;
    /**
     * 验证邮箱的验证码
     * @mock 123456
     */
    private String code;
}
