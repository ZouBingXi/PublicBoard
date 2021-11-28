package cn.szuer.publicboard.service.Impl;

import cn.szuer.publicboard.mapper.SubjectInfoMapper;
import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.mapper.SubjectStateMapper;
import cn.szuer.publicboard.model.SubjectInfo;
import cn.szuer.publicboard.model.SubjectInfoExample;
import cn.szuer.publicboard.model.SubjectState;
import cn.szuer.publicboard.model.SubjectType;
import cn.szuer.publicboard.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    private SubjectTypeMapper subjectTypeMapper;
    private SubjectStateMapper subjectStateMapper;

    @Override
    public List<SubjectInfo> getAll() {
        SubjectInfoExample example = new SubjectInfoExample();
        return subjectInfoMapper.selectByExample(example);
    }

    @Override
    public boolean addSubject(SubjectInfo subjectInfo) {
        SubjectInfo subject = subjectInfoMapper.selectByPrimaryKey(subjectInfo.getSubjectid());
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(subjectInfo.getSubjecttype());
        SubjectState subjectState = null;
        if(subject!=null||subjectType==null)
            return false;
        else
        {
            String subjectTitle = subjectInfo.getSubjecttitle();
            String content = subjectInfo.getContent();
            //check the length of subjectTitle and content
            if(0<subjectTitle.length() && subjectTitle.length()<=30 && 0<content.length() && content.length()<=1000)
            {
                //add subject and subjectState into table
                subjectInfo.setSendtime(new Date());
                subjectState.setSubjectid(subjectInfo.getSubjectid());
                subjectState.setHotstate((byte) 0);
                subjectState.setTopstate((byte) 0);
                subjectState.setExaminestate((byte) 0);
                int subject_res = subjectInfoMapper.insertSelective(subjectInfo);
                int newState_res = subjectStateMapper.insertSelective(subjectState);
                if(subject_res==1 && newState_res==1) //add subject success
                    return true;
                else  //inser error
                    return false;
            }
            else//can't pass
            {
                 return false;
            }

        }
    }

}
