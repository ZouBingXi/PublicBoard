package cn.szuer.publicboard.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

// import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.LoginParam;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.utils.Page;

public interface UserService {
    

    List<UserDto> getAll();
    PageInfo<UserDto> getByPage(int pageNum, int pageSize);
    
    UserDto login(LoginParam loginParam);
    boolean addUser(UserInfo userInfo);

}
