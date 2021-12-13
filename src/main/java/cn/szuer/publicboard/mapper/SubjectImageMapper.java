package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectImage;
import cn.szuer.publicboard.model.SubjectImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectImageMapper {
    int countByExample(SubjectImageExample example);

    int deleteByExample(SubjectImageExample example);

    int deleteByPrimaryKey(String imageuuid);

    int insert(SubjectImage record);

    int insertSelective(SubjectImage record);

    List<SubjectImage> selectByExample(SubjectImageExample example);

    SubjectImage selectByPrimaryKey(String imageuuid);

    int updateByExampleSelective(@Param("record") SubjectImage record, @Param("example") SubjectImageExample example);

    int updateByExample(@Param("record") SubjectImage record, @Param("example") SubjectImageExample example);

    int updateByPrimaryKeySelective(SubjectImage record);

    int updateByPrimaryKey(SubjectImage record);
}