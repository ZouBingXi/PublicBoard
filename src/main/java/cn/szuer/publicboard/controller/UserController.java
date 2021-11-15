package cn.szuer.publicboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.model.User;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.UserService;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;
    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<UserInfo> addUser(UserInfo userInfo)
    {
        if (userService.addUser(userInfo))
        {
            return new BaseResponse<UserInfo>(200,"注册成功",userInfo);
        }
        return new BaseResponse<UserInfo>(500,"注册失败,该学号已被注册",userInfo);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    // @PostMapping("/login")
    // public BaseResponse<User> login(HttpServletRequest request,User user)
    // {
    //     if (userService.login(user))
    //     {
    //         request.getSession().setAttribute("User",user);
    //     }
    //     return null;
    // }

    /**
     * 查看用户表
     * @param page 页数
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/show")
    public BaseResponse<List<UserInfo>> showUsers(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                @RequestParam(name = "size",required = true,defaultValue = "10")int size)
    {
       return null;
    }

    @GetMapping("/all")
    public BaseResponse<List<UserInfo>> showall()
    {
        System.out.println("yes");
        return new BaseResponse<List<UserInfo>>(200, "success", userService.getAll());
    }

    @GetMapping("/test")
    public String test()
    {
        return "yes";
    }
}
