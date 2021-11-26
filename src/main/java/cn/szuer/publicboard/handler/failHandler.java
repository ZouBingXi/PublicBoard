package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class failHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response,AuthenticationException e) throws IOException, ServletException
    {
        //设置返回类型
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out=response.getWriter();

        //获取用户信息
        BaseResponse<UserDto> baseResponse=new BaseResponse<>(500,"登录失败,用户名或密码有误");

        //将返回信息写入响应体中
        String res=JSON.toJSONString(baseResponse);
        out.print(res);
    }
}
