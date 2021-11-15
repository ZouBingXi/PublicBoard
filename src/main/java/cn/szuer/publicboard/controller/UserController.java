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
     * @param user
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<UserInfo> addUser(User user)
    {
        return null;
    }

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
     @PostMapping("/login")
     public BaseResponse<UserInfo> login(HttpServletRequest request,UserInfo userInfo)
     {
         UserInfo res=userService.login(userInfo);
         if (res!=null)
         {
             request.getSession().setAttribute("UserInfo",res);
             return new BaseResponse<UserInfo>(200,"登录成功",res);
         }
         return new BaseResponse<UserInfo>(500,"登陆失败,请检查用户名或密码",userInfo);
     }

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
