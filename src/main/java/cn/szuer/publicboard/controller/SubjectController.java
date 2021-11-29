package cn.szuer.publicboard.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    SubjectConverter subjectConverter;
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
        return new BaseResponse<PageInfo<SubjectSendDto>>(200, "success", subjectService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑话题
     * @param addSubjectParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<SubjectSendDto> add(@RequestBody AddSubjectParam addSubjectParam)
    {
        int res = subjectService.add(addSubjectParam);
        SubjectSendDto subjectSendDto = subjectConverter.AddSubjectParam2SubjectSendDto(addSubjectParam);
        if(res==21)
        {
            return new BaseResponse<SubjectSendDto>(500,"发布失败，账号不能为空!",subjectSendDto);
        }
        else if(res==22) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败，标题不能为空!", subjectSendDto);
        }
        else if(res==23) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!内容不能为空!", subjectSendDto);
        }
        else if(res==24) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!话题类型不能为空!", subjectSendDto);
        }
        else if(res==25) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!账号不存在!", subjectSendDto);
        }
        else if(res==26) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!当前账号被禁用!", subjectSendDto);
        }
        else if(res==27) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!话题类型不存在!", subjectSendDto);
        }
        else if(res==28) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!话题类型被禁用!", subjectSendDto);
        }
        else if(res==29) {
            return new BaseResponse<SubjectSendDto>(500, "发布失败!数据库错误", subjectSendDto);
        }
        else if(res==11) {
            return new BaseResponse<SubjectSendDto>(200, "发布成功！", subjectSendDto);
        }
        else if(res==12) {
            return new BaseResponse<SubjectSendDto>(200, "发布成功，使用匿名账号！", subjectSendDto);
        }
        return new BaseResponse<SubjectSendDto>(500, "发送失败，未知错误！", subjectSendDto);
    }

}