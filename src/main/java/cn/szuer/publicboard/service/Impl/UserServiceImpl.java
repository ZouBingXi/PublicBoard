package cn.szuer.publicboard.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.UserInfoExample;
import cn.szuer.publicboard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> getAll() {
        UserInfoExample example = new UserInfoExample(); 
        return userInfoMapper.selectByExample(example);
    }
    
    
}
