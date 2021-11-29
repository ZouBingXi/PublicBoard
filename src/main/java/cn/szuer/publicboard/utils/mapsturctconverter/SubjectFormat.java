package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.mapper.SubjectTypeMapper;
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
    public String idToname(Integer subjecttype)
    {
        return subjectTypeMapper.selectByPrimaryKey(subjecttype).getTypename();
    }

}
