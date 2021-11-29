package cn.szuer.publicboard.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;


/**
 * 帖子管理
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    @Autowired
    NewsService newsService;
    @Autowired
    NewsConverter newsConverter;
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
        return new BaseResponse<PageInfo<NewsSendDto>>(200, "success", newsService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑帖子
     * @param addNewsParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<NewsSendDto> add(@RequestBody AddNewsParam addNewsParam)
    {
        int res = newsService.add(addNewsParam);
        NewsSendDto newsSendDto = newsConverter.AddNewsParam2NewsSendDto(addNewsParam);

        if(res==21)
        {
            return new BaseResponse<NewsSendDto>(500,"发布失败，账号不能为空!",newsSendDto);
        }
        else if(res==22) {
            return new BaseResponse<NewsSendDto>(500, "发布失败，标题不能为空!", newsSendDto);
        }
        else if(res==23) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!内容不能为空!", newsSendDto);
        }
        else if(res==24) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!帖子类型不能为空!", newsSendDto);
        }
        else if(res==25) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!账号不存在!", newsSendDto);
        }
        else if(res==26) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!当前账号被禁用!", newsSendDto);
        }
        else if(res==27) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!帖子类型不存在!", newsSendDto);
        }
        else if(res==28) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!帖子类型被禁用!", newsSendDto);
        }
        else if(res==29) {
            return new BaseResponse<NewsSendDto>(500, "发布失败!数据库错误", newsSendDto);
        }
        else if(res==11) {
            return new BaseResponse<NewsSendDto>(200, "发布成功！", newsSendDto);
        }
        else if(res==12) {
            return new BaseResponse<NewsSendDto>(200, "发布成功，使用匿名账号！", newsSendDto);
        }
        return new BaseResponse<NewsSendDto>(500, "发送失败，未知错误！", newsSendDto);
    }

}