package cn.szuer.publicboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.model.SubjectInfo;
import cn.szuer.publicboard.reponse.BaseResponse;

/**
 * 话题管理
 */
@RestController
@RequestMapping("/SubjectInfo")
public class SubjectController
{
    /**
     * 查看话题表
     * @param page 页数
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/show")
    public BaseResponse<List<SubjectInfo>> showPosts(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                                 @RequestParam(name = "size",required = true,defaultValue = "10")int size)
    {
        return null;
    }

    /**
     * 编辑话题
     * @param SubjectInfo
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<SubjectInfo> addPost(SubjectInfo SubjectInfo)
    {
        return null;
    }
}