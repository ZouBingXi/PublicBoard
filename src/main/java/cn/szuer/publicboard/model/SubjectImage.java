package cn.szuer.publicboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectImage {
    private String imageuuid;

    private Integer subjectid;

    public String getImageuuid() {
        return imageuuid;
    }

    public void setImageuuid(String imageuuid) {
        this.imageuuid = imageuuid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }
}