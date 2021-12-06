package cn.szuer.publicboard.service.Impl;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserInfoMapper userInfoMapper;

    //注入converter,实现类在target里可以看到，由mapstruct自动生成
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto addUser(RegisterParam registerParam)
    {
        //查找数据库中有没有该学号的记录
        UserInfo user=userInfoMapper.selectByPrimaryKey(registerParam.getUserid());

        //该学号已注册
        if (user!=null)
            return null;

        else
        {
            //向数据库中插入数据,注册账户
            user=new UserInfo();
            user.setUserid(registerParam.getUserid());
            user.setUsername(String.valueOf(registerParam.getUserid()));
            user.setLogintime(new Date());
            user.setPassword(registerParam.getPassword());
            user.setEmail(registerParam.getEmail());
            int res=userInfoMapper.insertSelective(user);
            if (res==1)
                return userConverter.UserInfo2UserDto(user);
            else
                return null;
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
     * @param  pageNum,pageSize
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
