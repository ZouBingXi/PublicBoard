package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.model.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

//交给spring容器管理，并且引入格式转换类
@Mapper(componentModel = "spring",uses = {SubjectFormat.class})
public interface SubjectConverter{

    SubjectConverter INSTANCE = Mappers.getMapper(SubjectConverter.class);
    @Mapping(source = "subjecttypeid",target = "subjecttypename")
    SubjectSendDto SubjectInfo2SubjectSendDto(SubjectInfo subjectInfo);
    List<SubjectSendDto> SubjectInfos2SubjectSendDtos(List<SubjectInfo> subjectInfos);

    //AddSubjectParam转换成SubjectSendDto
    SubjectSendDto AddSubjectParam2SubjectSendDto(AddSubjectParam addSubjectParam);
    List<SubjectSendDto> AddSubjectParams2SubjectSendDtos(List<AddSubjectParam> addSubjectParams);


}
