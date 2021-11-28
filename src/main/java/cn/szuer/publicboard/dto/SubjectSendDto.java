package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubjectSendDto {

    private Integer userid;

    private String username;

    private String subjecttitle;

    private String content;

    private Date sendtime;

    private Integer viewnum;

    private Integer likenum;

    private String examinestate;

    private String typename;

    private List<String> imageaddress;
}
