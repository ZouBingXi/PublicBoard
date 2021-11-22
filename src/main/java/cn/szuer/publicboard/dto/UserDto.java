package cn.szuer.publicboard.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * 用户账号
     * @mock 2019192018 
     */
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户状态
     */
    private String banstate;

    /**
     * 用户类型
     */
    private String usertype;

    /**
     * 登录时间
     */
    private String logintime;

}
