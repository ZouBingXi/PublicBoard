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
     * @mock 张三
     */
    private String username;

    /**
     * 邮箱
     * @mock 2019192000@szu.edu.cn
     */
    private String email;


    /**
     * 头像
     * @mock "05fe71557e164b32b503708271365dfd.png"
     */
    private String headimage;


    /**
     * 用户状态
     * @mock 封禁状态
     */
    private String banstate;

    /**
     * 用户类型
     * @mock 管理员
     */
    private String usertype;

    /**
     * 登录时间
     */
    private String logintime;

    /**
     * 匿名模式
     */
    private Boolean anonymousmode;

}
