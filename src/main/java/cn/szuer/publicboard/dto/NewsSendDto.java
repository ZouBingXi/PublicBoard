package cn.szuer.publicboard.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NewsSendDto {

  private Integer newsid;

  private Integer userid;

  private String typename;

  private String newstitle;

  private String content;

  private Date sendtime;

  private Integer viewnum;

  private Integer likenum;
}
