package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsImage;
import cn.szuer.publicboard.model.NewsImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsImageMapper {
    int countByExample(NewsImageExample example);

    int deleteByExample(NewsImageExample example);

    int deleteByPrimaryKey(String imageuuid);

    int insert(NewsImage record);

    int insertSelective(NewsImage record);

    List<NewsImage> selectByExample(NewsImageExample example);

    NewsImage selectByPrimaryKey(String imageuuid);

    int updateByExampleSelective(@Param("record") NewsImage record, @Param("example") NewsImageExample example);

    int updateByExample(@Param("record") NewsImage record, @Param("example") NewsImageExample example);

    int updateByPrimaryKeySelective(NewsImage record);

    int updateByPrimaryKey(NewsImage record);
}