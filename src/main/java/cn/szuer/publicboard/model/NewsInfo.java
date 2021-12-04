package cn.szuer.publicboard.model;

import java.util.Date;

public class NewsInfo {
    private Integer newsid;

    private Integer userid;

    private String newstitle;

    private Date sendtime;

    private Integer viewnum;

    private Integer likenum;

    private Integer newstypeid;

    private Boolean anonymousstate;

    private Boolean topstate;

    private Boolean hotstate;

    private Boolean examinestate;

    private String content;

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
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

    public Integer getNewstypeid() {
        return newstypeid;
    }

    public void setNewstypeid(Integer newstypeid) {
        this.newstypeid = newstypeid;
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