package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.UserTypeMapper;
import cn.szuer.publicboard.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectFormat {

    @Autowired(required = false)
    private SubjectTypeMapper subjectTypeMapper;
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    private String UseridToUsername(Integer userid)
    {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userid);
        return userInfo.getUsername();
    }


}
