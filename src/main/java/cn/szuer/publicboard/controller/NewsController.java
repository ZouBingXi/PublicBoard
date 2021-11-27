package cn.szuer.publicboard.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.dto.NewsReceiveDto;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.News;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;
import cn.szuer.publicboard.vo.NewsVo;


/**
 * 帖子管理
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    @Autowired
    NewsService newsService;
    /**
     * 查看帖子表
     * @param page 页数
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/admin/checknews")
    public BaseResponse<PageInfo<NewsSendDto>> showNews(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                          @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        List<NewsVo> newsVos = new ArrayList<>();
        return new BaseResponse<PageInfo<NewsSendDto>>(200, "sucess", newsService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑帖子
     * @param newsReceiveDto
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<NewsSendDto> add(NewsReceiveDto newsReceiveDto)
    {
        int res = newsService.addNews(newsReceiveDto);
        NewsSendDto newsSendDto = newsConverter.NewsReceive2SubejctSend(newsReceiveInfo);
        if(res==1)
        {
            return new BaseResponse<NewsSendDto>(200,"已成功发布",newsSendDto);
        }
        return new BaseResponse<NewsSendDto>(500,"发布失败，请检查内容字数是否大于20，小于1000！",newsSendDto);
    }

}