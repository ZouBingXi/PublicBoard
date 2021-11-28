package cn.szuer.publicboard.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.model.SubjectInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.SubjectService;


/**
 * 话题管理
 */
@RestController
@RequestMapping("/subject")
public class SubjectController
{
    @Autowired
    SubjectService subjectService;
    /**
     * 查看话题表
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checksubject")
    public BaseResponse<PageInfo<SubjectSendDto>> showSubject(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                              @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<SubjectSendDto>>(200, "sucess", subjectService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑话题
     * @param addSubjectParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<SubjectSendDto> add(AddSubjectParam addSubjectParam)
    {
        int res = subjectService.addSubject(addSubjectParam);
        SubjectConverter subjectConverter = null;
        SubjectSendDto subjectSendDto = subjectConverter.AddSubjectParam2SubjectSendDto(addSubjectParam);
        if(res==21)
        {
            return new BaseResponse<SubjectSendDto>(500,"发布失败，当前用户处于封禁状态！",subjectSendDto);
        }
        else if(res==22) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败，话题类型错误！", subjectSendDto);
        }
        else if(res==23) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!", subjectSendDto);
        }
        else if(res==11) {
            return new BaseResponse<SubjectSendDto>(200, "发布成功！", subjectSendDto);
        }
        else if(res==12) {
            return new BaseResponse<SubjectSendDto>(500, "发布成功，使用匿名账号！", subjectSendDto);
        }
        return new BaseResponse<SubjectSendDto>(500, "发送失败，未知错误！", subjectSendDto);
    }

}