package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.SubjectReply;
import cn.szuer.publicboard.model.SubjectReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectReplyMapper {
    int countByExample(SubjectReplyExample example);

    int deleteByExample(SubjectReplyExample example);

    int deleteByPrimaryKey(Integer replyid);

    int insert(SubjectReply record);

    int insertSelective(SubjectReply record);

    List<SubjectReply> selectByExampleWithBLOBs(SubjectReplyExample example);

    List<SubjectReply> selectByExample(SubjectReplyExample example);

    SubjectReply selectByPrimaryKey(Integer replyid);

    int updateByExampleSelective(@Param("record") SubjectReply record, @Param("example") SubjectReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectReply record, @Param("example") SubjectReplyExample example);

    int updateByExample(@Param("record") SubjectReply record, @Param("example") SubjectReplyExample example);

    int updateByPrimaryKeySelective(SubjectReply record);

    int updateByPrimaryKeyWithBLOBs(SubjectReply record);

    int updateByPrimaryKey(SubjectReply record);
}