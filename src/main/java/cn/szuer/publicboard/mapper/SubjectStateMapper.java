package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectState;
import cn.szuer.publicboard.model.SubjectStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectStateMapper {
    int countByExample(SubjectStateExample example);

    int deleteByExample(SubjectStateExample example);

    int deleteByPrimaryKey(Integer subjectid);

    int insert(SubjectState record);

    int insertSelective(SubjectState record);

    List<SubjectState> selectByExample(SubjectStateExample example);

    SubjectState selectByPrimaryKey(Integer subjectid);

    int updateByExampleSelective(@Param("record") SubjectState record, @Param("example") SubjectStateExample example);

    int updateByExample(@Param("record") SubjectState record, @Param("example") SubjectStateExample example);

    int updateByPrimaryKeySelective(SubjectState record);

    int updateByPrimaryKey(SubjectState record);
}