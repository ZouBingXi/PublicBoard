package cn.szuer.publicboard.controller;

import java.util.List;

import cn.szuer.publicboard.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    SubjectService subjectService;
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

    @GetMapping("/all")
    public BaseResponse<List<SubjectInfo>> showall()
    {
        System.out.println("ok");
        return new BaseResponse<List<SubjectInfo>>(200, "add success", subjectService.getAll());
    }

    @GetMapping("/test")
    public String test()
    {
        return "success add";
    }

    /**
     * 编辑话题
     * @param subjectInfo
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<SubjectInfo> addSubject(SubjectInfo subjectInfo)
    {
        boolean res = subjectService.addSubject(subjectInfo);
        if(res)
        {
            return new BaseResponse<SubjectInfo>(200,"已成功发布",subjectInfo);
        }
        return new BaseResponse<SubjectInfo>(500,"发布失败，请检查帖子内容字数是否小于1000！",subjectInfo);
    }

}