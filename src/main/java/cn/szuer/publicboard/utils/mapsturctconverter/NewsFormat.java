package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.model.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsFormat {

    @Autowired(required = false)
    private NewsTypeMapper newsTypeMapper;

    /**
     * 将帖子类型编号转换成类型名
     * @param newstype
     * @return
     */
    private String UseridToUsername(Integer newstype)
    {
        NewsType newsType = newsTypeMapper.selectByPrimaryKey(newstype);
        return newsType.getTypename();
    }


}
