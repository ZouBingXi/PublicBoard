package cn.szuer.publicboard.dto;

import lombok.Data;

@Data
public class SubjectsDto {

    private Integer Subjectid;

    private Integer UserID;

    private String SubjectTitle;

    private String Content;

    private Date SendTime;

    private Integer ViewNum;

    private Integer LikeNUM;
}
