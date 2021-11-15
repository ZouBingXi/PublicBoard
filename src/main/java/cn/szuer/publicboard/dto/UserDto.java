package cn.szuer.publicboard.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

    private Integer userid;

    private String username;

    private Byte banstate;

    private Integer usertype;

    private Date logintime;

}
