package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsState;
import cn.szuer.publicboard.model.NewsStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsStateMapper {
    int countByExample(NewsStateExample example);

    int deleteByExample(NewsStateExample example);

    int deleteByPrimaryKey(Integer newsid);

    int insert(NewsState record);

    int insertSelective(NewsState record);

    List<NewsState> selectByExample(NewsStateExample example);

    NewsState selectByPrimaryKey(Integer newsid);

    int updateByExampleSelective(@Param("record") NewsState record, @Param("example") NewsStateExample example);

    int updateByExample(@Param("record") NewsState record, @Param("example") NewsStateExample example);

    int updateByPrimaryKeySelective(NewsState record);

    int updateByPrimaryKey(NewsState record);
}