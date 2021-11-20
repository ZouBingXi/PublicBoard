package cn.szuer.publicboard.utils.mapsturctconverter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.model.UserInfo;

//交给spring容器管理，并且引入格式转换类
@Mapper(componentModel = "spring", uses = {UserFormat.class})
public interface UserConverter {
    
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    // @Mapping(source = "userType.typename", target = "usertype")
    // @Mapping(source = "userInfo.banstate", target = "banstate")
    // @Mapping(source = "userType.typename", target = "usertype")
    //由于属性字段名称一致，这里不需要mapping注解
    UserDto UserInfo2UserDto(UserInfo userInfo);
    //实现list的拷贝
    List<UserDto> UserInfos2UserDtos(List<UserInfo> userInfos);

    UserInfo userDto2UserInfo(UserDto userDto);
}
