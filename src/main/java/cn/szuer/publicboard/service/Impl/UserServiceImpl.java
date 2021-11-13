package cn.szuer.publicboard.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szuer.publicboard.mapper.UserMapper;
import cn.szuer.publicboard.model.User;
import cn.szuer.publicboard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean add(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
