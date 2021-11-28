package cn.szuer.publicboard.controller;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checknews")
    public BaseResponse<PageInfo<NewsSendDto>> showNews(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                              @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<NewsSendDto>>(200, "sucess", newsService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑帖子
     * @param addNewsParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<NewsSendDto> add(AddNewsParam addNewsParam)
    {
        int res = newsService.addNews(addNewsParam);
        NewsConverter newsConverter = null;
        NewsSendDto newsSendDto = newsConverter.AddNewsParam2NewsSendDto(addNewsParam);
        if(res==21)
        {
            return new BaseResponse<NewsSendDto>(500,"发布失败，当前用户处于封禁状态！",newsSendDto);
        }
        else if(res==22) {
            return new BaseResponse<NewsSendDto>(500, "发布失败，帖子类型错误！", newsSendDto);
        }
        else if(res==23) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!", newsSendDto);
        }
        else if(res==11) {
            return new BaseResponse<NewsSendDto>(200, "发布成功！", newsSendDto);
        }
        else if(res==12) {
            return new BaseResponse<NewsSendDto>(500, "发布成功，使用匿名账号！", newsSendDto);
        }
        return new BaseResponse<NewsSendDto>(500, "发送失败，未知错误！", newsSendDto);
    }

}