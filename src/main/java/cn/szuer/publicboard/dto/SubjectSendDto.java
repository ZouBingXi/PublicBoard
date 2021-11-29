package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubjectSendDto {

    private Integer userid;

    private String subjecttitle;

    private String content;

    private String typename;
}
