package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectInfo;
import cn.szuer.publicboard.model.SubjectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectInfoMapper {
    int countByExample(SubjectInfoExample example);

    int deleteByExample(SubjectInfoExample example);

    int deleteByPrimaryKey(Integer subjectid);

    int insert(SubjectInfo record);

    int insertSelective(SubjectInfo record);

    List<SubjectInfo> selectByExampleWithBLOBs(SubjectInfoExample example);

    List<SubjectInfo> selectByExample(SubjectInfoExample example);

    SubjectInfo selectByPrimaryKey(Integer subjectid);

    int updateByExampleSelective(@Param("record") SubjectInfo record, @Param("example") SubjectInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectInfo record, @Param("example") SubjectInfoExample example);

    int updateByExample(@Param("record") SubjectInfo record, @Param("example") SubjectInfoExample example);

    int updateByPrimaryKeySelective(SubjectInfo record);

    int updateByPrimaryKeyWithBLOBs(SubjectInfo record);

    int updateByPrimaryKey(SubjectInfo record);

    List<SubjectInfo> selectAll();

    int updateViewNum(Integer subjectid, Integer num);
}