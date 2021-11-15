package cn.szuer.publicboard.service;

import java.util.List;

import cn.szuer.publicboard.model.User;
import cn.szuer.publicboard.model.UserInfo;

public interface UserService {
    
    List<UserInfo> getAll();

    UserInfo login(UserInfo user);
    
    boolean addUser(UserInfo userInfo);
}
