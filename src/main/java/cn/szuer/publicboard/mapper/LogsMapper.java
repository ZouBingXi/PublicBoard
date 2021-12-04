package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.Logs;
import cn.szuer.publicboard.model.LogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogsMapper {
    int countByExample(LogsExample example);

    int deleteByExample(LogsExample example);

    int deleteByPrimaryKey(Integer logsid);

    int insert(Logs record);

    int insertSelective(Logs record);

    List<Logs> selectByExample(LogsExample example);

    Logs selectByPrimaryKey(Integer logsid);

    int updateByExampleSelective(@Param("record") Logs record, @Param("example") LogsExample example);

    int updateByExample(@Param("record") Logs record, @Param("example") LogsExample example);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}