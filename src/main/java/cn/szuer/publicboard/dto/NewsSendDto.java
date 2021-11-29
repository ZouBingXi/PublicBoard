package cn.szuer.publicboard.dto;

import lombok.Data;

@Data
public class NewsSendDto {

    private Integer userid;

    private String newstitle;

    private String content;

    private String typename;
}
