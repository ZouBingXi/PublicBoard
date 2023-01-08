package cn.szuer.publicboard.utils.mapsturctconverter;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.szuer.publicboard.mapper.UserTypeMapper;

@Component
public class UserFormat {

    @Value("${minio.endPoint}")
    private String endPoint;

    @Value("${minio.bucket}")
    private String bucketName;

    //注入UserTypeMapper
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 将boolean转换成字符串
     * @param banstate
     * @return
     */
    public String ByteToString(Boolean banstate)
    {
        if(banstate)
            return "封禁状态";
        else
            return "正常状态";
    }

    /**
     * 将Integer转换成对应的用户类型字符串
     * @param usertype
     * @return
     */
    public String IntegerToString(Integer typeid)
    {
        //返回用户表中usertype对应的用户类型名称
        return userTypeMapper.selectByPrimaryKey(typeid).getTypename(); 
    }

    @Named("uuid2url")
    public String UUIDtoURL(String headimage)
    {
      return endPoint+bucketName+"/avatar/" + headimage;
    }

}
