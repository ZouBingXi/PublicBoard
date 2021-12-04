package cn.szuer.publicboard.model;

import java.util.Date;

public class Logs {
    private Integer logsid;

    private Integer userid;

    private Integer operatetype;

    private String operateinfo;

    private Integer targetid;

    private Date operatetime;

    public Integer getLogsid() {
        return logsid;
    }

    public void setLogsid(Integer logsid) {
        this.logsid = logsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(Integer operatetype) {
        this.operatetype = operatetype;
    }

    public String getOperateinfo() {
        return operateinfo;
    }

    public void setOperateinfo(String operateinfo) {
        this.operateinfo = operateinfo;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }
}