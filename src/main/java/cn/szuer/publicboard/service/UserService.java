package cn.szuer.publicboard.service;

import java.util.List;

import cn.szuer.publicboard.dto.param.ChangePasswordParam;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

// import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.model.UserInfo;

public interface UserService {
    

    List<UserDto> getAll();
   
    PageInfo<UserDto> getByPage(int pageNum, int pageSize);

    boolean addUser(RegisterParam registerParam);

    UserDto getProfile();

    int updateProfile(MultipartFile multipartFile, UserDto userDto);

    boolean changePassword(ChangePasswordParam param);

    boolean switchMode();

}
