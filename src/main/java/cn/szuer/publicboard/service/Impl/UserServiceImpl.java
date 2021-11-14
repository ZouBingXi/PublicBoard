package cn.szuer.publicboard.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
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

    @Override
    public boolean addUser(UserInfo userInfo)
    {
        UserInfo user=userInfoMapper.selectByPrimaryKey(userInfo.getUserid());
        if (user!=null)
            return false;
        else
        {
            userInfo.setUsername(String.valueOf(userInfo.getUserid()));
            userInfo.setLogintime(new Date());
            userInfo.setHeadimage(new byte[]{1});
            int res=userInfoMapper.insertSelective(userInfo);
            if (res==1)
                return true;
            else
                return false;
        }
    }


}
