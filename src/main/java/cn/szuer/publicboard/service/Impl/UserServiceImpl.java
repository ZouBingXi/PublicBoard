package cn.szuer.publicboard.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

// import com.github.pagehelper.PageHelper;
// import com.github.pagehelper.PageInfo;

import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.stereotype.Service;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.LoginParam;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.UserInfoExample;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.Page;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserInfoMapper userInfoMapper;

    //注入converter,实现类在target里可以看到，由mapstruct自动生成
    @Autowired
    private UserConverter userConverter;
        
    public UserDto login(LoginParam loginParam)
    {
        Integer id=loginParam.getUserid();
        String password=loginParam.getPassword();
        UserInfo res=userInfoMapper.selectByPrimaryKey(id);
        if (res!=null&&password.equals(res.getPassword()))
            //只返回Dto中的属性
            return userConverter.UserInfo2UserDto(res);
        else
            return null;
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
            userInfo.setHeadimage(new byte[]{60,63,120,109,108,32,118,101,114,115,105,111,110,61,34,49,46,48,34,32,115,116,97,110,100,97,108,111,110,101,61,34,110,111,34,63,62,60,33,68,79,67,84,89,80,69,32,115,118,103,32,80,85,66,76,73,67,32,34,45,47,47,87,51,67,47,47,68,84,68,32,83,86,71,32,49,46,49,47,47,69,78,34,32,34,104,116,116,112,58,47,47,119,119,119,46,119,51,46,111,114,103,47,71,114,97,112,104,105,99,115,47,83,86,71,47,49,46,49,47,68,84,68,47,115,118,103,49,49,46,100,116,100,34,62,60,115,118,103,32,99,108,97,115,115,61,34,105,99,111,110,34,32,119,105,100,116,104,61,34,50,48,48,112,120,34,32,104,101,105,103,104,116,61,34,50,48,48,46,48,48,112,120,34,32,118,105,101,119,66,111,120,61,34,48,32,48,32,49,48,50,52,32,49,48,50,52,34,32,118,101,114,115,105,111,110,61,34,49,46,49,34,32,120,109,108,110,115,61,34,104,116,116,112,58,47,47,119,119,119,46,119,51,46,111,114,103,47,50,48,48,48,47,115,118,103,34,62,60,112,97,116,104,32,102,105,108,108,61,34,35,51,51,51,51,51,51,34,32,100,61,34,77,53,49,50,32,55,52,46,54,54,54,54,54,55,67,50,55,48,46,57,51,51,51,51,51,32,55,52,46,54,54,54,54,54,55,32,55,52,46,54,54,54,54,54,55,32,50,55,48,46,57,51,51,51,51,51,32,55,52,46,54,54,54,54,54,55,32,53,49,50,83,50,55,48,46,57,51,51,51,51,51,32,57,52,57,46,51,51,51,51,51,51,32,53,49,50,32,57,52,57,46,51,51,51,51,51,51,32,57,52,57,46,51,51,51,51,51,51,32,55,53,51,46,48,54,54,54,54,55,32,57,52,57,46,51,51,51,51,51,51,32,53,49,50,32,55,53,51,46,48,54,54,54,54,55,32,55,52,46,54,54,54,54,54,55,32,53,49,50,32,55,52,46,54,54,54,54,54,55,122,77,50,56,56,32,56,49,48,46,54,54,54,54,54,55,99,48,45,49,50,51,46,55,51,51,51,51,51,32,49,48,48,46,50,54,54,54,54,55,45,50,50,52,32,50,50,52,45,50,50,52,83,55,51,54,32,54,56,54,46,57,51,51,51,51,51,32,55,51,54,32,56,49,48,46,54,54,54,54,54,55,99,45,54,49,46,56,54,54,54,54,55,32,52,54,46,57,51,51,51,51,51,45,49,52,48,46,56,32,55,52,46,54,54,54,54,54,55,45,50,50,52,32,55,52,46,54,54,54,54,54,54,115,45,49,54,50,46,49,51,51,51,51,51,45,50,55,46,55,51,51,51,51,51,45,50,50,52,45,55,52,46,54,54,54,54,54,54,122,32,109,49,50,56,45,51,56,52,99,48,45,53,51,46,51,51,51,51,51,51,32,52,50,46,54,54,54,54,54,55,45,57,54,32,57,54,45,57,54,115,57,54,32,52,50,46,54,54,54,54,54,55,32,57,54,32,57,54,45,52,50,46,54,54,54,54,54,55,32,57,54,45,57,54,32,57,54,45,57,54,45,52,50,46,54,54,54,54,54,55,45,57,54,45,57,54,122,32,109,51,55,55,46,54,32,51,50,56,46,53,51,51,51,51,51,99,45,49,57,46,50,45,57,54,45,56,53,46,51,51,51,51,51,51,45,49,55,52,46,57,51,51,51,51,51,45,49,55,52,46,57,51,51,51,51,51,45,50,49,49,46,50,32,51,50,45,50,57,46,56,54,54,54,54,55,32,53,49,46,50,45,55,48,46,52,32,53,49,46,50,45,49,49,55,46,51,51,51,51,51,51,32,48,45,56,55,46,52,54,54,54,54,55,45,55,50,46,53,51,51,51,51,51,45,49,54,48,45,49,54,48,45,49,54,48,115,45,49,54,48,32,55,50,46,53,51,51,51,51,51,45,49,54,48,32,49,54,48,99,48,32,52,54,46,57,51,51,51,51,51,32,49,57,46,50,32,56,55,46,52,54,54,54,54,55,32,53,49,46,50,32,49,49,55,46,51,51,51,51,51,51,45,56,57,46,54,32,51,54,46,50,54,54,54,54,55,45,49,53,53,46,55,51,51,51,51,51,32,49,49,53,46,50,45,49,55,52,46,57,51,51,51,51,52,32,50,49,49,46,50,45,53,53,46,52,54,54,54,54,55,45,54,54,46,49,51,51,51,51,51,45,57,49,46,55,51,51,51,51,51,45,49,52,57,46,51,51,51,51,51,51,45,57,49,46,55,51,51,51,51,51,45,50,52,51,46,50,32,48,45,50,48,52,46,56,32,49,54,56,46,53,51,51,51,51,51,45,51,55,51,46,51,51,51,51,51,51,32,51,55,51,46,51,51,51,51,51,51,45,51,55,51,46,51,51,51,51,51,51,83,56,56,53,46,51,51,51,51,51,51,32,51,48,55,46,50,32,56,56,53,46,51,51,51,51,51,51,32,53,49,50,99,48,32,57,51,46,56,54,54,54,54,55,45,51,52,46,49,51,51,51,51,51,32,49,55,55,46,48,54,54,54,54,55,45,57,49,46,55,51,51,51,51,51,32,50,52,51,46,50,122,34,32,47,62,60,47,115,118,103,62});
            int res=userInfoMapper.insertSelective(userInfo);
            if (res==1)
                return true;
            else
                return false;
        }
    }
    
    
    @Override
    public List<UserDto> getAll() {
        // UserInfoExample example = new UserInfoExample(); 
        // List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        List<UserDto> userDtos =  userConverter.UserInfos2UserDtos(userInfos);
        return userDtos;
    }

    /**
     * 根据页面参数返回对应数量的records
     * @param  pageNum,pageSiz
     * @return PageInfo<UserDto>
     */
    @Override
    public PageInfo<UserDto> getByPage(int pageNum, int pageSize) {
        
        PageHelper.startPage(pageNum, pageSize);
        // UserInfoExample example = new UserInfoExample();
        // List<UserInfo> userInfos = userInfoMapper.selectByExample(new UserInfoExample());
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        //使用converter将Userinfo拷贝到userDto
        List<UserDto> userDtos =  userConverter.UserInfos2UserDtos(userInfos);
        //先根据查出来的userinfos生成PageInfo
        PageInfo pageInfo = new PageInfo<>(userInfos);
        //再进行setList操作，转成userDtos
        pageInfo.setList(userDtos);
    
        return pageInfo;

    }
    
    
    // private List<UserDto> copyList(List<UserInfo> records) {
    //     List<UserDto> userDtos = new ArrayList<>();
    //     for(UserInfo record : records){
    //         userDtos.add(copy(record));
    //     }
    //     return userDtos;
    // }
    
    // private UserDto copy(UserInfo userInfo){
    //     UserDto userDto = new UserDto();
    //     BeanUtils.copyProperties(userInfo, userDto);
    //     if(userInfo.getUsertype()==1)
    //         userDto.setUsertype("管理员");
    //     else 
    //         userDto.setUsertype("普通用户");
    //     // if(userInfo.getBanstate()==1)
    //     //     userDto.setBanstate("禁用状态");
    //     // else
    //     //     userDto.setBanstate("正常状态");
    //     SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     userDto.setLogintime(sf.format(userInfo.getLogintime()));
    //     return userDto;
    // }


}
