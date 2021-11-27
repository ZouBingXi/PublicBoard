package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewsReceiveDto {

    private Integer userid;

    private String newstitle;

    private String content;

    private Integer newstype;

    private Date sendtime;

}
