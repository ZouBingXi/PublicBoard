package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubjectSendDto {

    private Integer subjectid;

    private Integer userid;

    private String typename;

    private String subjecttitle;

    private String content;

    private String sendtime;

    private Integer viewnum;

    private Integer likenum;

}
