package cn.szuer.publicboard.controller;

import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.ChangeEmailParam;
import cn.szuer.publicboard.dto.param.ChangePasswordParam;
import cn.szuer.publicboard.dto.param.ForgetPasswordParam;
import com.github.pagehelper.PageInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.AuthenticationUtil;

import javax.servlet.http.HttpSession;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;


    @Autowired
    private AuthenticationUtil authenticationUtil;
    
    /**
     * 用户注册
     * @param registerParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<UserDto> addUser(@RequestBody RegisterParam registerParam,HttpSession session)
    {
        int res=userService.addUser(registerParam,session);
        if (res==1)
            return new BaseResponse(500,"请先验证邮箱");
        else if (res==2)
            return new BaseResponse(500,"不是验证时的邮箱");
        else if (res==3)
            return new BaseResponse(500,"验证码超时");
        else if (res==4)
            return new BaseResponse(500,"验证码错误");
        else if (res==5)
            return new BaseResponse(500,"注册失败,该邮箱已被注册");
        else if (res==6)
            return new BaseResponse(500,"注册失败,该学号已被注册");
        else
        {
            session.removeAttribute("code");
            session.removeAttribute("codeTime");
            session.removeAttribute("email");
            return new BaseResponse(200,"注册成功");
        }
    }

    /**
     * 查看用户表
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checkuser")
    public BaseResponse<PageInfo<UserDto>> showUsers(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        // List<UserVo> userVos = new ArrayList<>();
        return new BaseResponse<PageInfo<UserDto>>(200, "success", userService.getByPage(pageNum, pageSize));

    }

    /**
     * 返回所有用户
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_管理员')")
    public BaseResponse<List<UserDto>> showall()
    {
        return new BaseResponse<List<UserDto>>(200, "success", userService.getAll());

    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('ROLE_普通用户')")
    public String test()
    {
        return "yes";
    }

    /**
     * 我是谁
     * @return
     */
    @GetMapping("/whoami")
    public Integer whoami()
    {
        return authenticationUtil.getAuthenticatedId();
    }

    /**
     * 查看个人信息
     * @return
     */
    @GetMapping("/showprofile")
    public BaseResponse<UserDto> showProfile()
    {
         return new BaseResponse<UserDto>(200, "success", userService.getProfile());
    }

    /**
     * 修改个人信息
     * @param multipartFile
     * @param userDto
     * @return
     */
    @PostMapping("/updateprofile")
    public BaseResponse updateProfile(@RequestParam(name = "avatar", required = false) MultipartFile multipartFile, UserDto userDto)
    {
         int res = userService.updateProfile(multipartFile, userDto);

         if(res==9001)
         {
            return new BaseResponse(500, "头像上传失败");
         }
         else if(res == 22)
         {
            return new BaseResponse(500, "个人信息更新失败");
         }
         else
         {
            return new BaseResponse(200, "个人信息更新成功");
         }

    }

    /**
     * 修改密码
     * @param param
     * @return
     */
    @PostMapping("/changepassword")
    public BaseResponse changePassword(@RequestBody ChangePasswordParam param)
    {
        boolean ifSuccess=userService.changePassword(param);

        if (ifSuccess)
        {
            return new BaseResponse(200,"修改成功");
        }
        return new BaseResponse(500,"原密码错误");
    }

    /**
     * 开启/关闭匿名模式
     * @return
     */
    @PostMapping("/switchmode")
    public BaseResponse switchMode()
    {
    
       if(userService.switchMode())
          return new BaseResponse(200,"切换成功");
        else
          return new BaseResponse(500, "出错了，请重试");

    }

    
    /**
     * 发送验证码
     * @param to   验证码发往的邮箱
     * @param flag "exist"表示需要邮箱已注册(忘记密码);     "noexist"表示需要邮箱未注册(注册，修改邮箱)
     * @return
     */
    @GetMapping("/sendcode")
    public BaseResponse sendCode(HttpSession session,String to, String flag)
    {
        boolean res=userService.ifExist(to);
        if (flag=="exist")
        {
            if (!res)
                return new BaseResponse(500,"该邮箱还未注册");
        }
        else if(flag=="noexist")
        {
            if(res)
                return new BaseResponse(500, "该邮箱已被注册");
        }

        String code=userService.sendCode(to);
        session.setAttribute("code",code);
        session.setAttribute("codeTime",new Date());
        session.setAttribute("email",to);
        return new BaseResponse(200,"发送成功");
    }

    /**
     * 忘记密码
     * @param param
     * @param session
     * @return
     */
    @PostMapping("/forget")
    public BaseResponse forgetPassword(@RequestBody ForgetPasswordParam param, HttpSession session)
    {
        int res=userService.forget(session,param);
        if (res==1)
            return new BaseResponse(500,"请先验证邮箱");
        else if (res==2)
            return new BaseResponse(500,"不是验证时的邮箱");
        else if (res==3)
            return new BaseResponse(500,"验证码超时");
        else if (res==4)
            return new BaseResponse(500,"验证码错误");
        else
        {
            session.removeAttribute("code");
            session.removeAttribute("codeTime");
            session.removeAttribute("email");
            return new BaseResponse(200,"修改成功");
        }
    }

     /**
     * 修改邮箱
     * @param param
     * @param session
     * @return
     */
    @PostMapping("/changeemail")
    public BaseResponse changeEmail(@RequestBody ChangeEmailParam param,HttpSession session)
    {
        int res=userService.changeEmail(session,param);
        if (res==1)
            return new BaseResponse(500,"请先验证邮箱");
        else if (res==2)
            return new BaseResponse(500,"不是验证时的邮箱");
        else if (res==3)
            return new BaseResponse(500,"验证码超时");
        else if (res==4)
            return new BaseResponse(500,"验证码错误");
        else
        {
            session.removeAttribute("code");
            session.removeAttribute("codeTime");
            session.removeAttribute("email");
            return new BaseResponse(200,"修改成功");
        }        
    }


}
