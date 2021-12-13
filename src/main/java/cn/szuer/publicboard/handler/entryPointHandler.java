package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用于用户未登录时访问的返回
 * 状态码：403
 */
@Component
public class entryPointHandler implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException e) throws IOException, ServletException
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        BaseResponse baseResponse=new BaseResponse(403,"用户未登录,请先登录");

        PrintWriter out=response.getWriter();
        out.write(JSON.toJSONString(baseResponse));
        out.flush();
    }
}
