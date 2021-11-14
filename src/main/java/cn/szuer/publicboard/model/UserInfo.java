package cn.szuer.publicboard.model;

import java.util.Date;

public class UserInfo {
    private Integer userid;

    private String username;

    private String password;

    private Byte anonymousstate;

    private Byte banstate;

    private Integer usertype;

    private Date logintime;

    private byte[] headimage;

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

    public Byte getAnonymousstate() {
        return anonymousstate;
    }

    public void setAnonymousstate(Byte anonymousstate) {
        this.anonymousstate = anonymousstate;
    }

    public Byte getBanstate() {
        return banstate;
    }

    public void setBanstate(Byte banstate) {
        this.banstate = banstate;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public byte[] getHeadimage() {
        return headimage;
    }

    public void setHeadimage(byte[] headimage) {
        this.headimage = headimage;
    }
}