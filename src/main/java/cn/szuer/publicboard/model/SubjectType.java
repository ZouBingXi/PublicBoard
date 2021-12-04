package cn.szuer.publicboard.model;

public class SubjectType {
    private Integer subjecttypeid;

    private String typename;

    private Boolean typestate;

    public Integer getSubjecttypeid() {
        return subjecttypeid;
    }

    public void setSubjecttypeid(Integer subjecttypeid) {
        this.subjecttypeid = subjecttypeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Boolean getTypestate() {
        return typestate;
    }

    public void setTypestate(Boolean typestate) {
        this.typestate = typestate;
    }
}