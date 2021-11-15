package cn.szuer.publicboard.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.UserInfoExample;
import cn.szuer.publicboard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserDto> getAll() {
        UserInfoExample example = new UserInfoExample(); 
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        List<UserDto> userDtos =  copyList(userInfos);
        return userDtos;
    }

    @Override
    public PageInfo<UserDto> getByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfoExample example = new UserInfoExample(); 
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        List<UserDto> userDtos =  copyList(userInfos);
        return new PageInfo<UserDto>(userDtos);
    }
    

    private List<UserDto> copyList(List<UserInfo> records) {
        List<UserDto> userDtos = new ArrayList<>();
        for(UserInfo record : records){
            userDtos.add(copy(record));

        }
        return userDtos;
    }
    
    private UserDto copy(UserInfo userInfo){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userInfo, userDto);
        return userDto;
    }


    
}
