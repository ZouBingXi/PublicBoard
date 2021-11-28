package cn.szuer.publicboard.service.Impl;

import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
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

import java.util.ArrayList;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    @Autowired(required = false)
    private SubjectInfoMapper subjectInfoMapper;

    @Autowired(required = false)
    private SubjectTypeMapper subjectTypeMapper;

    @Autowired(required = false)
    private SubjectStateMapper subjectStateMapper;

    @Autowired
    private SubjectConverter subjectConverter;

    @Override
    public List<SubjectSendDto> getAll() {
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectAll();
        List<SubjectSendDto> subjectSendDtos = subjectConverter.SubjectInfos2SubjectSendDtos(subjectInfos);
        return subjectSendDtos;
    }

    @Override
    public int addSubject(AddSubjectParam addSubjectParam) {
        SubjectInfo subject = null;          //话题信息
        SubjectState subjectState = null;    //话题状态
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addSubjectParam.getUserid());
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(addSubjectParam.getSubjecttype());

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
        criteria.andExaminestateIsNotNull();
        subjectid = subjectStateMapper.countByExample(example) + 1;

        //设置subjectinfo各属性值
        subject.setSubjectid(subjectid);
        subject.setUserid(addSubjectParam.getUserid());
        subject.setSubjecttype(addSubjectParam.getSubjecttype());
        subject.setSubjecttitle(addSubjectParam.getSubjecttitle());
        subject.setContent(addSubjectParam.getContent());
        subject.setSendtime(new Date());
        subject.setViewnum(0);
        subject.setLikenum(0);
        //设置subjectstate各属性
        subjectState.setSubjectid(subjectid);
        subjectState.setTopstate((byte)0);
        subjectState.setHotstate((byte)0);
        subjectState.setExaminestate((byte)0);

        System.out.println("/////test/////subject");
        System.out.println(subject);
        System.out.println("/////test/////state");
        System.out.println(subjectState);

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
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectAll();
        List<SubjectSendDto> subjectSendDtos =  subjectConverter.SubjectInfos2SubjectSendDtos(subjectInfos);
        return new PageInfo<>(subjectSendDtos);
    }


}
