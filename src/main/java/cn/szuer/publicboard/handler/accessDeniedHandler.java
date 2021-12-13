package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用于在用户无权限访问该接口时的返回
 * 状态码：402
 */
@Component
public class accessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request,HttpServletResponse response,AccessDeniedException e) throws IOException, ServletException
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        BaseResponse baseResponse=new BaseResponse(402,"该用户没有访问权限");

        PrintWriter out=response.getWriter();
        out.write(JSON.toJSONString(baseResponse));
        out.flush();
    }
}
