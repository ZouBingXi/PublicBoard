package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.UserType;
import cn.szuer.publicboard.model.UserTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTypeMapper {
    int countByExample(UserTypeExample example);

    int deleteByExample(UserTypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(UserType record);

    int insertSelective(UserType record);

    List<UserType> selectByExample(UserTypeExample example);

    UserType selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") UserType record, @Param("example") UserTypeExample example);

    int updateByExample(@Param("record") UserType record, @Param("example") UserTypeExample example);

    int updateByPrimaryKeySelective(UserType record);

    int updateByPrimaryKey(UserType record);
}