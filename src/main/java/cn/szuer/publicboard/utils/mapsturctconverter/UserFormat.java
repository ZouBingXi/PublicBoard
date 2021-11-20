package cn.szuer.publicboard.utils.mapsturctconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.szuer.publicboard.mapper.UserTypeMapper;

@Component
public class UserFormat {
    

    //注入UserTypeMapper
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 将Byte转换成字符串
     * @param banstate
     * @return
     */
    public String ByteToString(Byte banstate)
    {
        if(banstate==1)
            return "封禁状态";
        else
            return "正常状态";
    }

    /**
     * 将Integer转换成对应的用户类型字符串
     * @param usertype
     * @return
     */
    public String IntegerToString(Integer usertype)
    {
        //返回用户表中usertype对应的用户类型名称
        return userTypeMapper.selectByPrimaryKey(usertype).getTypename(); 
    }
}
