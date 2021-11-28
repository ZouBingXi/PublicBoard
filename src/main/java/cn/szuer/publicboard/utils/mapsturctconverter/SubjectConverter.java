package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.mapper.SubjectInfoMapper;
import cn.szuer.publicboard.mapper.SubjectStateMapper;
import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class SubjectConverter {
    public SubjectSendDto SubjectReceive2SubjectSend(AddSubjectParam addSubjectParam)
    {
        SubjectSendDto subjectSendDto = new SubjectSendDto();
        UserInfoMapper userInfoMapper = null;
        SubjectInfoMapper subjectInfoMapper = null;
        SubjectTypeMapper subjectTypeMapper = null;
        SubjectStateMapper subjectStateMapper = null;

        //使用BeanUtils.copyProperties方法进行SubjectInfo和SubjectSendDto之间部分属性的赋值
        BeanUtils.copyProperties(addSubjectParam, subjectSendDto);

        //将用户信息表里的用户名取出，并赋值给subjectSendDto.username
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addSubjectParam.getUserid());
        subjectSendDto.setUsername(userInfo.getUsername());

        //将subjectInfo里的subjecttype从类型编号转换成类型名称，并赋值给subjectSendDto.subjecttype
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(addSubjectParam.getSubjecttype());
        subjectSendDto.setTypename(subjectType.getTypename());

        //从话题状态表取出该话题的状态信息，并在subjectSendDto对应赋值
        SubjectInfoExample example = new SubjectInfoExample();
        SubjectInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSubjecttitleEqualTo(addSubjectParam.getSubjecttitle());
        criteria.andUseridEqualTo(addSubjectParam.getUserid());
        criteria.andSubjecttypeEqualTo(addSubjectParam.getSubjecttype());
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectByExample(example);
        SubjectState subjectState = subjectStateMapper.selectByPrimaryKey(subjectInfos.get(0).getSubjectid());

        //赋值审核状态
        if (subjectState.getExaminestate()==1)
            subjectSendDto.setExaminestate("审核通过");
        else
            subjectSendDto.setExaminestate("审核中");

        //获取图像信息
        List<String> image_path = null;
        image_path.add("E://SubjectImage//1//image1.jpg");
        subjectSendDto.setImageaddress(image_path);

        return subjectSendDto;

    }
}
