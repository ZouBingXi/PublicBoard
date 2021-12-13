package cn.szuer.publicboard.controller;

import java.util.List;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
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
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.AuthenticationUtil;

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
    public BaseResponse<UserDto> addUser(@RequestBody RegisterParam registerParam)
    {
        boolean ifsuccess=userService.addUser(registerParam);
        if (ifsuccess)
        {
            return new BaseResponse<UserDto>(200,"注册成功");
        }
        return new BaseResponse<UserDto>(500,"注册失败,该学号已被注册");

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
     * 
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_管理员')")
    public BaseResponse<List<UserDto>> showall()
    {
        return new BaseResponse<List<UserDto>>(200, "success", userService.getAll());

    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('ROLE_普通用户')")
    public String test()
    {
        return "yes";
    }

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


}
