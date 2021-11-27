package cn.szuer.publicboard.service;

import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.News;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.NewsReceiveDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NewsService {

    List<NewsSendDto> getAll();
    PageInfo<NewsSendDto> getByPage(int pageNum, int pageSize);

    int addNews(NewsReceiveDto newsReceiveDto);
}
