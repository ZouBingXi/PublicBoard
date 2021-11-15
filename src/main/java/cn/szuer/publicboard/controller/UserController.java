package cn.szuer.publicboard.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.model.User;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.vo.UserVo;

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
    public BaseResponse<User> addUser(User user)
    {
        return null;
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
    @GetMapping("/admin/checkuser")
    public BaseResponse<PageInfo<UserDto>> showUsers(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<UserDto>>(200, "sucess", userService.getByPage(pageNum, pageSize));
    }
    
    @GetMapping("/all")
    public BaseResponse<List<UserDto>> showall()
    {
        return new BaseResponse<List<UserDto>>(200, "sucess", userService.getAll());
    }

    // @GetMapping("/test")
    // public String test()
    // {
    //     return "yes";
    // }
}
