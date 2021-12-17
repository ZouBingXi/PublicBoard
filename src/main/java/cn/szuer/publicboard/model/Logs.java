package cn.szuer.publicboard.model;

import java.util.Date;

public class Logs {
    private Integer logsid;

    private Integer targetid;

    private String operatetype;

    private String operateinfo;

    private Date operatetime;

    public Integer getLogsid() {
        return logsid;
    }

    public void setLogsid(Integer logsid) {
        this.logsid = logsid;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
    }

    public String getOperateinfo() {
        return operateinfo;
    }

    public void setOperateinfo(String operateinfo) {
        this.operateinfo = operateinfo;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }
}