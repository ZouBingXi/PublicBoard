package cn.szuer.publicboard.model;

public class NewsState {
    private Integer newsid;

    private Byte topstate;

    private Byte hotstate;

    private Byte examinestate;

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Byte getTopstate() {
        return topstate;
    }

    public void setTopstate(Byte topstate) {
        this.topstate = topstate;
    }

    public Byte getHotstate() {
        return hotstate;
    }

    public void setHotstate(Byte hotstate) {
        this.hotstate = hotstate;
    }

    public Byte getExaminestate() {
        return examinestate;
    }

    public void setExaminestate(Byte examinestate) {
        this.examinestate = examinestate;
    }
}