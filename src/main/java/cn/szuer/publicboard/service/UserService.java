package cn.szuer.publicboard.service;

import java.util.List;

import cn.szuer.publicboard.model.UserInfo;

public interface UserService {
    
    List<UserInfo> getAll();
    boolean addUser(UserInfo userInfo);
}
