package cn.szuer.publicboard.service;

import java.util.List;

import cn.szuer.publicboard.dto.param.ChangeEmailParam;
import cn.szuer.publicboard.dto.param.ChangePasswordParam;
import cn.szuer.publicboard.dto.param.ForgetPasswordParam;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

// import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.model.UserInfo;

import javax.servlet.http.HttpSession;

public interface UserService {
    

    List<UserDto> getAll();
   
    PageInfo<UserDto> getByPage(int pageNum, int pageSize);

    int addUser(RegisterParam registerParam,HttpSession session);

    UserDto getProfile();

    int updateProfile(MultipartFile multipartFile, UserDto userDto);

    boolean changePassword(ChangePasswordParam param);

    boolean switchMode();

    String sendCode(String to);

    int forget(HttpSession session,ForgetPasswordParam param);

    boolean ifExist(String email);

    int changeEmail(HttpSession session, ChangeEmailParam changeEmailParam);

}
