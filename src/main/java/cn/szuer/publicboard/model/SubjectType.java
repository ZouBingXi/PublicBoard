package cn.szuer.publicboard.model;

public class SubjectType {
    private Integer subjecttypeid;

    private String typename;

    private Byte typestate;

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

    public Byte getTypestate() {
        return typestate;
    }

    public void setTypestate(Byte typestate) {
        this.typestate = typestate;
    }
}