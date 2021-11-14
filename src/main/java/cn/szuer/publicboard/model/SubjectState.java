package cn.szuer.publicboard.model;

public class SubjectState {
    private Integer subjectid;

    private Byte topstate;

    private Byte hotstate;

    private Byte examinestate;

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
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