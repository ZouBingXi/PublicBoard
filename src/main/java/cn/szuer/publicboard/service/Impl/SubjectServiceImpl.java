package cn.szuer.publicboard.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.SubjectInfoMapper;
import cn.szuer.publicboard.mapper.SubjectStateMapper;
import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.service.SubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    private SubjectInfoMapper subjectInfoMapper;
    private SubjectTypeMapper subjectTypeMapper;
    private SubjectStateMapper subjectStateMapper;

    public SubjectServiceImpl() {
    }

    @Override
    public List<SubjectSendDto> getAll() {
        SubjectInfoExample example = new SubjectInfoExample();
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectAll();
        List<SubjectSendDto> subjectSendDtos = copyList(subjectInfos);
        return subjectSendDtos;
    }

    @Override
    public int addSubject(AddSubjectParam subjectReceiveDto) {
        SubjectInfo subject = null;          //话题信息
        SubjectState subjectState = null;    //话题状态
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(subjectReceiveDto.getUserid());
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(subjectReceiveDto.getSubjecttype());

        //判断用户是否被封禁,被封禁返回21，代表用户封禁，插入失败
        if (userInfo.getBanstate()==0)
            return 21;

        //判断话题类型状态是否禁用，禁用返回22，代表该类型被禁用，插入失败
        if(subjectType.getTypestate()==0)
            return 22;

        //获取话题表记录数
        Integer subjectid = 0;
        SubjectStateExample example = new  SubjectStateExample();
        SubjectStateExample.Criteria criteria = example.createCriteria();
        criteria.andExaminestateBetween((byte)0,(byte)1);
        subjectid = subjectStateMapper.countByExample(example) + 1;
        //设置subjectinfo各属性值
        subject.setSubjectid(subjectid);
        subject.setUserid(subjectReceiveDto.getUserid());
        subject.setSubjecttype(subjectReceiveDto.getSubjecttype());
        subject.setSubjecttitle(subjectReceiveDto.getSubjecttitle());
        subject.setContent(subjectReceiveDto.getContent());
        subject.setSendtime(new Date());
        subject.setViewnum(0);
        subject.setLikenum(0);
        //设置subjectstate各属性
        subjectState.setSubjectid(subjectid);
        subjectState.setTopstate((byte)0);
        subjectState.setHotstate((byte)0);
        subjectState.setExaminestate((byte)0);

        //判断是否匿名
        if(userInfo.getAnonymousstate()==0)//正常用户状态
        {
            int res1 = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            int res2 = subjectStateMapper.insertSelective(subjectState);  //插入到话题状态表
            if(res1!=0&&res2!=0)
                return 11;
            else
                return 23;
        }
        else//匿名用户状态
        {
            int res1 = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            int res2 = subjectStateMapper.insertSelective(subjectState);  //插入到话题状态表
            if(res1!=0&&res2!=0)
                return 12;
            else
                return 23;
        }
    }

    @Override
    public PageInfo<SubjectSendDto> getByPage(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        SubjectInfoExample example = new SubjectInfoExample();
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectByExample(example);
        System.out.println(subjectInfos.toString());
        List<SubjectSendDto> subjectSendDtos =  copyList(subjectInfos);
        return new PageInfo<>(subjectSendDtos);
    }

    private List<SubjectSendDto> copyList(List<SubjectInfo> records)
    {
        List<SubjectSendDto> subjectSendDtos = new ArrayList<>();
        for(SubjectInfo record : records)
        {
            subjectSendDtos.add(copy(record));
        }
        return subjectSendDtos;
    }

    private SubjectSendDto copy(SubjectInfo subjectInfo){
        SubjectSendDto subjectSendDto = new SubjectSendDto();

        //使用BeanUtils.copyProperties方法进行SubjectInfo和SubjectSendDto之间部分属性的赋值
        BeanUtils.copyProperties(subjectInfo, subjectSendDto);

        //将用户信息表里的用户名取出，并赋值给subjectSendDto.username
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(subjectInfo.getUserid());
        subjectSendDto.setUsername(userInfo.getUsername());

        //将newInfo里的subjecttype从类型编号转换成类型名称，并赋值给subjectSendDto.subjecttype
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(subjectInfo.getSubjecttype());
        subjectSendDto.setTypename(subjectType.getTypename());

        //从话题状态表取出该话题的状态信息，并在subjectSendDto对应赋值
        SubjectState subjectState = subjectStateMapper.selectByPrimaryKey(subjectInfo.getSubjectid());

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
