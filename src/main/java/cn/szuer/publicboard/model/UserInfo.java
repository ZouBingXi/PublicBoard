package cn.szuer.publicboard.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo {
    private Integer userid;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String headimage;

    private Boolean anonymousmode;

    private Boolean banstate;

    private Integer typeid;

    @JsonIgnore
    private Date logintime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public Boolean getAnonymousmode() {
        return anonymousmode;
    }

    public void setAnonymousmode(Boolean anonymousmode) {
        this.anonymousmode = anonymousmode;
    }

    public Boolean getBanstate() {
        return banstate;
    }

    public void setBanstate(Boolean banstate) {
        this.banstate = banstate;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}