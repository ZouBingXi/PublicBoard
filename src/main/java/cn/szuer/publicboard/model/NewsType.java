package cn.szuer.publicboard.model;

public class NewsType {
    private Integer newstypeid;

    private String typename;

    private Boolean typestate;

    public Integer getNewstypeid() {
        return newstypeid;
    }

    public void setNewstypeid(Integer newstypeid) {
        this.newstypeid = newstypeid;
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