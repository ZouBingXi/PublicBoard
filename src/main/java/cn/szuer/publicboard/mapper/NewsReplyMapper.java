package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsReply;
import cn.szuer.publicboard.model.NewsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsReplyMapper {
    int countByExample(NewsReplyExample example);

    int deleteByExample(NewsReplyExample example);

    int deleteByPrimaryKey(Integer replyid);

    int insert(NewsReply record);

    int insertSelective(NewsReply record);

    List<NewsReply> selectByExampleWithBLOBs(NewsReplyExample example);

    List<NewsReply> selectByExample(NewsReplyExample example);

    NewsReply selectByPrimaryKey(Integer replyid);

    int updateByExampleSelective(@Param("record") NewsReply record, @Param("example") NewsReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsReply record, @Param("example") NewsReplyExample example);

    int updateByExample(@Param("record") NewsReply record, @Param("example") NewsReplyExample example);

    int updateByPrimaryKeySelective(NewsReply record);

    int updateByPrimaryKeyWithBLOBs(NewsReply record);

    int updateByPrimaryKey(NewsReply record);
}