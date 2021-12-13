package cn.szuer.publicboard.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.MinioUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.SubjectImageMapper;
import cn.szuer.publicboard.mapper.SubjectInfoMapper;
import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.service.SubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SubjectInfoMapper subjectInfoMapper;

    @Autowired
    private SubjectTypeMapper subjectTypeMapper;

    @Autowired
    private SubjectImageMapper subjectImageMapper;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private AuthenticationUtil authenticationUtil;

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
        //话题信息
        SubjectInfo subject = new SubjectInfo();
        //发布话题人信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(addSubjectParam.getUserid());
        //话题类型信息
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(addSubjectParam.getSubjecttypeid());

        //判断用户是否被封禁,被封禁返回26
        if (userInfo.getBanstate()==Boolean.TRUE)
            return 21;

        //判断话题类型状态是否禁用，禁用返回28
        if(subjectType.getTypestate()==Boolean.FALSE)
            return 22;

        //设置subjectinfo各属性值
        Integer id = null;
        subject.setSubjectid(id);
        subject.setUserid(addSubjectParam.getUserid());
        subject.setSubjecttitle(addSubjectParam.getSubjecttitle());
        subject.setContent(addSubjectParam.getContent());
        subject.setSubjecttypeid(addSubjectParam.getSubjecttypeid());
        subject.setSendtime(new Date());
        subject.setViewnum(0);
        subject.setLikenum(0);
        subject.setTopstate(Boolean.FALSE);
        subject.setHotstate(Boolean.FALSE);
        subject.setExaminestate(Boolean.FALSE);
        subject.setAnonymousstate(userInfo.getAnonymousmode());


        //判断是否匿名
        if(userInfo.getAnonymousmode()==Boolean.FALSE)//正常用户状态
        {
            int res = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            if(res!=0)
                return 11;
            else
                return 29;
        }
        else//匿名用户状态
        {
            int res = subjectInfoMapper.insertSelective(subject);        //插入到话题信息表
            if(res!=0)
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
        PageInfo pageInfo = new PageInfo<>(subjectInfos);
        //再进行setList操作
        pageInfo.setList(subjectSendDtos);
        return pageInfo;
    }


    @Override
    public int addWithImages(List<MultipartFile> multipartFiles, AddSubjectParam addSubjectParam) {
        //话题信息
        SubjectInfo subject = new SubjectInfo();
        //发布话题人信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authenticationUtil.getAuthenticatedId());

        //判断用户是否被封禁,被封禁返回26
        if (userInfo.getBanstate()==Boolean.TRUE)
            return 21;

        //设置subjectinfo各属性值
        Integer id = null;
        subject.setSubjectid(id);
        subject.setUserid(userInfo.getUserid());
        subject.setSubjecttitle(addSubjectParam.getSubjecttitle());
        subject.setContent(addSubjectParam.getContent());
        subject.setSubjecttypeid(addSubjectParam.getSubjecttypeid());
        subject.setSendtime(new Date());
        subject.setViewnum(0);
        subject.setLikenum(0);
        subject.setTopstate(Boolean.FALSE);
        subject.setHotstate(Boolean.FALSE);
        subject.setExaminestate(Boolean.FALSE);
        subject.setAnonymousstate(userInfo.getAnonymousmode());

        //将subject插入到话题信息表
        int res = subjectInfoMapper.insertSelective(subject);       

        //若插入失败直接返回
        if(res==0)
            return 29;

        //如果有图片
        if(multipartFiles!=null)
        {
            //上传图片到服务器, 并返回上传图片的返回体
            BaseResponse<List<String>> UploadResponse = minioUtil.uploadFile(multipartFiles, "subject");
            if(UploadResponse.getCode()==9001)
                return 9001;

            //从上传返回体中获得该话题的图片UUID
            List<String> subjectImageUUIDs = UploadResponse.getData();
            //获得话题ID,在进行插入后, 自增的ID会注入回subject
            Integer subjectid = subject.getSubjectid(); 

            //将话题ID和对应图片的UUID插入到帖子图片表中
            for(String subjectImageUUID: subjectImageUUIDs)
            {
                subjectImageMapper.insert(new SubjectImage(subjectImageUUID, subjectid));
            }
        }
     
        //判断是否匿名
        if(userInfo.getAnonymousmode()==Boolean.FALSE)//正常用户状态
        {
                return 11;
        }
        else//匿名用户状态
        {
                return 12;
        }
    }

}
