package cn.szuer.publicboard.service;

import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SubjectService {

    List<SubjectSendDto> getAll();
    PageInfo<SubjectSendDto> getByPage(int pageNum, int pageSize);

    int addSubject(AddSubjectParam addSubjectParam);
}
