package cn.szuer.publicboard.vo;

import lombok.Data;
import java.util.Date;

@Data
public class NewsVo {

    private Integer userid;

    private String username;

    private String newstitle;

    private String content;

    private Date sendtime;

    private Integer viewnum;

    private Integer likenum;

    private Boolean examinestate;

    private String typename;

    private String imageaddress [];
}
