package cn.szuer.publicboard.service;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.reponse.BaseResponse;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService {

    List<SubjectSendDto> getAll();
    PageInfo<SubjectSendDto> getByPage(int pageNum, int pageSize);
    BaseResponse<SubjectSendDto> view(Integer userid, Integer newsid);
    BaseResponse<PageInfo<SubjectSendDto>> viewDiffSubject(Integer typeid,Integer pageNum,Integer pageSize);
    BaseResponse<List<TypeSendDto>> getSubjectType();
    int add(AddSubjectParam addSubjectParam);

    int addWithImages(List<MultipartFile> multipartFiles, AddSubjectParam addSubjectParam);
}
