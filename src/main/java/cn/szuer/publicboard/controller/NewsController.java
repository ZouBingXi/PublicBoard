package cn.szuer.publicboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.reponse.BaseResponse;

/**
 * 帖子管理
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    /**
     * 查看帖子表
     * @param page 页数
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/show")
    public BaseResponse<List<NewsInfo>> showPosts(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                              @RequestParam(name = "size",required = true,defaultValue = "10")int size)
    {
        return null;
    }

    /**
     * 编辑帖子
     * @param news
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<NewsInfo> addPost(NewsInfo news)
    {
        return null;
    }
}
