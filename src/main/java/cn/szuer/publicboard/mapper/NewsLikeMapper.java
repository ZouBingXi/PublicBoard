package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsLikeExample;
import cn.szuer.publicboard.model.NewsLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsLikeMapper {
    int countByExample(NewsLikeExample example);

    int deleteByExample(NewsLikeExample example);

    int deleteByPrimaryKey(NewsLikeKey key);

    int insert(NewsLikeKey record);

    int insertSelective(NewsLikeKey record);

    List<NewsLikeKey> selectByExample(NewsLikeExample example);

    int updateByExampleSelective(@Param("record") NewsLikeKey record, @Param("example") NewsLikeExample example);

    int updateByExample(@Param("record") NewsLikeKey record, @Param("example") NewsLikeExample example);
}