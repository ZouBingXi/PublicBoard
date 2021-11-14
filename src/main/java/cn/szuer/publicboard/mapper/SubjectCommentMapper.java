package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectComment;
import cn.szuer.publicboard.model.SubjectCommentExample;
import cn.szuer.publicboard.model.SubjectCommentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectCommentMapper {
    int countByExample(SubjectCommentExample example);

    int deleteByExample(SubjectCommentExample example);

    int deleteByPrimaryKey(SubjectCommentKey key);

    int insert(SubjectComment record);

    int insertSelective(SubjectComment record);

    List<SubjectComment> selectByExampleWithBLOBs(SubjectCommentExample example);

    List<SubjectComment> selectByExample(SubjectCommentExample example);

    SubjectComment selectByPrimaryKey(SubjectCommentKey key);

    int updateByExampleSelective(@Param("record") SubjectComment record, @Param("example") SubjectCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectComment record, @Param("example") SubjectCommentExample example);

    int updateByExample(@Param("record") SubjectComment record, @Param("example") SubjectCommentExample example);

    int updateByPrimaryKeySelective(SubjectComment record);

    int updateByPrimaryKeyWithBLOBs(SubjectComment record);

    int updateByPrimaryKey(SubjectComment record);
}