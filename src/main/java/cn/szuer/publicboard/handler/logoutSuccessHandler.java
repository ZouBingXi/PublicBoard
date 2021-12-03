package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class logoutSuccessHandler implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException
    {
        //设置返回类型
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();

        //编写返回信息
        BaseResponse baseResponse=new BaseResponse<>(200,"用户注销成功");

        //将返回信息写入响应体中
        String res=JSON.toJSONString(baseResponse);
        out.print(res);
    }
}
