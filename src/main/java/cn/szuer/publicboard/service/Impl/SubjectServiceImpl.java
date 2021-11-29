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

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SubjectInfoMapper subjectInfoMapper;

    @Autowired
    private SubjectTypeMapper subjectTypeMapper;

    @Autowired
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
    public int add(AddSubjectParam addSubjectParam) {
        SubjectInfo subject = new SubjectInfo();          //话题信息
        SubjectState subjectState = new SubjectState();    //话题状态
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addSubjectParam.getUserid());
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(addSubjectParam.getSubjecttype());

        //判断账号是否为空,为空返回21
        if(addSubjectParam.getUserid()==null)
            return 21;

        //判断标题是否为空,为空返回22
        if(addSubjectParam.getSubjecttitle()==null)
            return 22;

        //判断内容是否为空,为空返回23
        if(addSubjectParam.getContent()==null)
            return 23;

        //判断类型是否为空,为空返回24
        if(addSubjectParam.getSubjecttype()==null)
            return 24;

        //判断userinfo表是否存在输入userid,不存在返回25
        if(userInfo==null)
            return 25;

        //判断用户是否被封禁,被封禁返回26
        if (userInfo.getBanstate()==1)
            return 26;

        //判断subjecttype表是否存在输入subjecttypeid,不存在返回27
        if(subjectType==null)
            return 27;

        //判断话题类型状态是否禁用，禁用返回28
        if(subjectType.getTypestate()==0)
            return 28;

        //获取话题表记录数
        Integer subjectid = 0;
        List<SubjectInfo> subjectInfos= subjectInfoMapper.selectAll();
        subjectid=subjectInfos.size();

        //设置subjectinfo各属性值
        subject.setSubjectid(subjectid+1);
        subject.setUserid(addSubjectParam.getUserid());
        subject.setSubjecttype(addSubjectParam.getSubjecttype());
        subject.setSubjecttitle(addSubjectParam.getSubjecttitle());
        subject.setContent(addSubjectParam.getContent());
        subject.setSendtime(new Date());
        subject.setViewnum(0);
        subject.setLikenum(0);
        //设置subjectstate各属性
        subjectState.setSubjectid(subjectid+1);
        Byte state = 0;
        subjectState.setTopstate(state);
        subjectState.setHotstate(state);
        subjectState.setExaminestate(state);


        //判断是否匿名
        if(userInfo.getAnonymousstate()==0)//正常用户状态
        {
            int res1 = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            int res2 = subjectStateMapper.insertSelective(subjectState);  //插入到话题状态表
            if(res1!=0&&res2!=0)
                return 11;
            else
                return 29;
        }
        else//匿名用户状态
        {
            int res1 = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            int res2 = subjectStateMapper.insertSelective(subjectState);  //插入到话题状态表
            if(res1!=0&&res2!=0)
                return 12;
            else
                return 29;
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
