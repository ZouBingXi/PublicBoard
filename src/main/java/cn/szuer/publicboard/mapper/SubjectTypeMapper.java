package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectType;
import cn.szuer.publicboard.model.SubjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectTypeMapper {
    int countByExample(SubjectTypeExample example);

    int deleteByExample(SubjectTypeExample example);

    int deleteByPrimaryKey(Integer subjecttypeid);

    int insert(SubjectType record);

    int insertSelective(SubjectType record);

    List<SubjectType> selectByExample(SubjectTypeExample example);

    SubjectType selectByPrimaryKey(Integer subjecttypeid);

    int updateByExampleSelective(@Param("record") SubjectType record, @Param("example") SubjectTypeExample example);

    int updateByExample(@Param("record") SubjectType record, @Param("example") SubjectTypeExample example);

    int updateByPrimaryKeySelective(SubjectType record);

    int updateByPrimaryKey(SubjectType record);
}