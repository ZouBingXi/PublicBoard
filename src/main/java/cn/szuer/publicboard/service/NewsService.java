package cn.szuer.publicboard.service;

import cn.szuer.publicboard.dto.NewsDetailSendDto;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddCommentParam;
import cn.szuer.publicboard.dto.param.AddNewsParam;

import cn.szuer.publicboard.dto.param.AddReplyParam;
import cn.szuer.publicboard.model.NewsInfo;

import cn.szuer.publicboard.dto.param.SearchParam;

import cn.szuer.publicboard.reponse.BaseResponse;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    List<NewsSendDto> getAll();

    PageInfo<NewsSendDto> getByPage(int pageNum, int pageSize);

    BaseResponse<NewsDetailSendDto> view(Integer newsid);

    BaseResponse<PageInfo<NewsSendDto>> viewDiffNews(Integer typeid,Integer pageNum,Integer pageSize);

    BaseResponse<List<TypeSendDto>> getNewsType();

    BaseResponse<List<Integer>> likeNews(Integer newsid);

    int add(AddNewsParam addNewsParam);

    int addWithImages(List<MultipartFile> multipartFiles, AddNewsParam addNewsParam);

    BaseResponse<NewsDetailSendDto> comment(AddCommentParam addCommentParam);

    BaseResponse<NewsDetailSendDto> reply(AddReplyParam addReplyParam);
  
    PageInfo<NewsSendDto> getMyNews(int pageNum, int pageSize);

    PageInfo<NewsSendDto> searchNews(SearchParam param);

}
