package cn.szuer.publicboard.model;

public class NewsType {
    private Integer newstypeid;

    private String typename;

    private Byte typestate;

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

    public Byte getTypestate() {
        return typestate;
    }

    public void setTypestate(Byte typestate) {
        this.typestate = typestate;
    }
}