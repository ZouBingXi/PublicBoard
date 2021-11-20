package cn.szuer.publicboard.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

// import cn.szuer.publicboard.enums.BanStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userid;

    private String username;

    private String banstate;

    private String usertype;

    private String logintime;

}
