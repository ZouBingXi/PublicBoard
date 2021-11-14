package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsComment;
import cn.szuer.publicboard.model.NewsCommentExample;
import cn.szuer.publicboard.model.NewsCommentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsCommentMapper {
    int countByExample(NewsCommentExample example);

    int deleteByExample(NewsCommentExample example);

    int deleteByPrimaryKey(NewsCommentKey key);

    int insert(NewsComment record);

    int insertSelective(NewsComment record);

    List<NewsComment> selectByExampleWithBLOBs(NewsCommentExample example);

    List<NewsComment> selectByExample(NewsCommentExample example);

    NewsComment selectByPrimaryKey(NewsCommentKey key);

    int updateByExampleSelective(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByExample(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByPrimaryKeySelective(NewsComment record);

    int updateByPrimaryKeyWithBLOBs(NewsComment record);

    int updateByPrimaryKey(NewsComment record);
}