package cn.szuer.publicboard.service;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    List<NewsSendDto> getAll();
    PageInfo<NewsSendDto> getByPage(int pageNum, int pageSize);

    int add(AddNewsParam addNewsParam);
    int addWithImages(List<MultipartFile> multipartFiles, AddNewsParam addNewsParam);
}
