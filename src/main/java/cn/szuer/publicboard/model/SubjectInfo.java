package cn.szuer.publicboard.model;

import java.util.Date;

public class SubjectInfo {
    private Integer subjectid;

    private Integer userid;

    private String subjecttitle;

    private Date sendtime;

    private Integer viewnum;

    private Integer likenum;

    private Integer subjecttypeid;

    private Boolean anonymousstate;

    private Boolean topstate;

    private Boolean hotstate;

    private Boolean examinestate;

    private String content;

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSubjecttitle() {
        return subjecttitle;
    }

    public void setSubjecttitle(String subjecttitle) {
        this.subjecttitle = subjecttitle;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getSubjecttypeid() {
        return subjecttypeid;
    }

    public void setSubjecttypeid(Integer subjecttypeid) {
        this.subjecttypeid = subjecttypeid;
    }

    public Boolean getAnonymousstate() {
        return anonymousstate;
    }

    public void setAnonymousstate(Boolean anonymousstate) {
        this.anonymousstate = anonymousstate;
    }

    public Boolean getTopstate() {
        return topstate;
    }

    public void setTopstate(Boolean topstate) {
        this.topstate = topstate;
    }

    public Boolean getHotstate() {
        return hotstate;
    }

    public void setHotstate(Boolean hotstate) {
        this.hotstate = hotstate;
    }

    public Boolean getExaminestate() {
        return examinestate;
    }

    public void setExaminestate(Boolean examinestate) {
        this.examinestate = examinestate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}