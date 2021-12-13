package cn.szuer.publicboard.service.Impl;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.mapper.SubjectImageMapper;
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
    public BaseResponse<SubjectSendDto> view(Integer userid,Integer subjectid)
    {
        SubjectSendDto subjectSendDto = new SubjectSendDto();
        //获取账号信息
        UserInfo user = userInfoMapper.selectByPrimaryKey(userid);

        //获取话题信息
        SubjectInfo subjectInfo = subjectInfoMapper.selectByPrimaryKey(subjectid);

        //判断账号是否被封禁
        if(user.getBanstate()==Boolean.TRUE)
            return new BaseResponse<>(500,"获取失败！账号处于封禁状态",subjectSendDto);

        //获取话题图像uuid
        SubjectImageExample example = new SubjectImageExample();
        SubjectImageExample.Criteria criteria = example.createCriteria();
        criteria.andSubjectidEqualTo(subjectInfo.getSubjectid());
        List<SubjectImage> imgs = subjectImageMapper.selectByExample(example);
        List<String> uuids  = new ArrayList<>();
        for (SubjectImage img:imgs)
        {
            uuids.add(img.getImageuuid());
        }

        //从服务器获取下载话题图像url
        List<String> imgList = new ArrayList<>();
        if(imgs.size()!=0)  //该话题有图像才向服务器请求下载图片
        {
            try {
                imgList = minioUtil.getDownloadUrls(uuids,"subject");
            } catch (Exception e) {
                e.printStackTrace();
                return new BaseResponse<>(500,"获取失败！",subjectSendDto);
            }
        }

        //获取发帖人信息
        UserInfo sender = userInfoMapper.selectByPrimaryKey(subjectInfo.getUserid());
        //获取发帖人头像url
        String headimg = null;
        try {
            headimg = minioUtil.getDownloadUrl(sender.getHeadimage(),"avatar");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置SubjectSendDto各属性
        subjectSendDto.setUserid(subjectInfo.getUserid());
        subjectSendDto.setUsername(sender.getUsername());
        subjectSendDto.setHeadimage(headimg);
        subjectSendDto.setSubjecttitle(subjectInfo.getSubjecttitle());
        subjectSendDto.setContent(subjectInfo.getContent());
        subjectSendDto.setViewnum(subjectInfo.getViewnum());
        subjectSendDto.setLikenum(subjectInfo.getLikenum());
        subjectSendDto.setAnonymousstate(subjectInfo.getAnonymousstate());
        subjectSendDto.setTopstate(subjectInfo.getTopstate());
        subjectSendDto.setHotstate(subjectInfo.getHotstate());
        subjectSendDto.setImgUrls(imgList);

        //将Date类型转换成时间戳
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(subjectInfo.getSendtime());
        subjectSendDto.setSendtime(time);
        //设置话题类型名字
        String typename = subjectTypeMapper.selectByPrimaryKey(subjectInfo.getSubjecttypeid()).getTypename();
        subjectSendDto.setSubjecttypename(typename);

        //更新浏览量
        int res = subjectInfoMapper.updateViewNum(subjectInfo.getSubjectid(),subjectInfo.getViewnum()+1);

        return new BaseResponse<>(200,"获取成功！",subjectSendDto);
    }


    @Override
    public BaseResponse<PageInfo<SubjectSendDto>> viewDiffSubject(Integer typeid,Integer pageNum,Integer pageSize)
    {
        List<SubjectSendDto> subjectSendDtos = new ArrayList<>();

        //设置页面属性
        PageHelper.startPage(pageNum, pageSize);

        //查找输入的话题类型的所有话题
        SubjectInfoExample example = new SubjectInfoExample();
        SubjectInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSubjecttypeidEqualTo(typeid);
        List<SubjectInfo> subjectInfos = subjectInfoMapper.selectByExample(example);

        //判断这种类型是否有话题
        if (subjectInfos.size()==0)
            return new BaseResponse(500,"该类型包含话题数为0，获取失败！");

        //将每条话题转化为subjectenddto
        for (SubjectInfo subjectInfo:subjectInfos)
        {
            SubjectSendDto subjectSendDto = new SubjectSendDto();

            //获取话题图像uuid
            SubjectImageExample example1 = new SubjectImageExample();
            SubjectImageExample.Criteria criteria1 = example1.createCriteria();
            criteria.andSubjectidEqualTo(subjectInfo.getSubjectid());
            List<SubjectImage> imgs = subjectImageMapper.selectByExample(example1);
            List<String> uuids  = new ArrayList<>();
            for (SubjectImage img:imgs)
            {
                uuids.add(img.getImageuuid());
            }

            //从服务器获取下载话题图像url
            List<String> imgList = new ArrayList<>();
            if(imgs.size()!=0)  //该话题有图像才向服务器请求下载图片
            {
                try {
                    imgList = minioUtil.getDownloadUrls(uuids,"subject");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new BaseResponse(500,"获取失败！");
                }
            }

            //获取发帖人信息
            UserInfo sender = userInfoMapper.selectByPrimaryKey(subjectInfo.getUserid());
            //获取发帖人头像url
            String headimg = null;
            try {
                headimg = minioUtil.getDownloadUrl(sender.getHeadimage(),"avatar");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //设置SubjectSendDto各属性
            subjectSendDto.setSubjectid(subjectInfo.getSubjectid());
            subjectSendDto.setUserid(subjectInfo.getUserid());
            subjectSendDto.setUsername(sender.getUsername());
            subjectSendDto.setHeadimage(headimg);
            subjectSendDto.setSubjecttitle(subjectInfo.getSubjecttitle());
            subjectSendDto.setContent(subjectInfo.getContent());
            subjectSendDto.setViewnum(subjectInfo.getViewnum());
            subjectSendDto.setLikenum(subjectInfo.getLikenum());
            subjectSendDto.setAnonymousstate(subjectInfo.getAnonymousstate());
            subjectSendDto.setTopstate(subjectInfo.getTopstate());
            subjectSendDto.setHotstate(subjectInfo.getHotstate());
            subjectSendDto.setImgUrls(imgList);
            //将Date类型转换成时间戳
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(subjectInfo.getSendtime());
            subjectSendDto.setSendtime(time);
            //设置话题类型名字
            String typename = subjectTypeMapper.selectByPrimaryKey(subjectInfo.getSubjecttypeid()).getTypename();
            subjectSendDto.setSubjecttypename(typename);

            //将subjectSendDto添加进List里
            subjectSendDtos.add(subjectSendDto);
        }

        PageInfo pageInfo = new PageInfo<>(subjectInfos);
        pageInfo.setList(subjectSendDtos);
        return new BaseResponse(200,"获取成功！",pageInfo);
    }


    @Override
    public BaseResponse<List<TypeSendDto>> getSubjectType()
    {
        //获取所有话题类型
        List<SubjectType> tmp = subjectTypeMapper.selectAll();
        List<TypeSendDto> typeSendDtos = new ArrayList<>();

        //去除禁用的话题类型
        for (SubjectType subejctType :tmp)
        {
            if(subejctType.getTypestate()!=Boolean.FALSE)
            {
                TypeSendDto typeSendDto = new TypeSendDto();
                typeSendDto.setTypeid(subejctType.getSubjecttypeid());
                typeSendDto.setTypename(subejctType.getTypename());
                typeSendDtos.add(typeSendDto);
            }
        }

        return new BaseResponse<>(200,"获取成功！",typeSendDtos);
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
