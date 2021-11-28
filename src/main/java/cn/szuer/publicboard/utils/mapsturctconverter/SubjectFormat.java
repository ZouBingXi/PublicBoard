package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.mapper.SubjectTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.UserTypeMapper;
import cn.szuer.publicboard.model.SubjectType;
import cn.szuer.publicboard.model.SubjectTypeExample;
import cn.szuer.publicboard.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectFormat {

    @Autowired(required = false)
    private SubjectTypeMapper subjectTypeMapper;

    /**
     * 将话题类型编号转换成类型名
     * @param subjecttype
     * @return
     */
    private String UseridToUsername(Integer subjecttype)
    {
        SubjectType subjectType = subjectTypeMapper.selectByPrimaryKey(subjecttype);
        return subjectType.getTypename();
    }


}
