package cn.szuer.publicboard.config.sessionStrategy;

import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 定义session过期的处理
 * 状态码：403
 */
@Component
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy
{
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException
    {
        HttpServletResponse response=event.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        BaseResponse baseResponse=new BaseResponse(403,"长时间未操作,或账号已被登录,请重新登录");

        PrintWriter out=response.getWriter();
        out.write(JSON.toJSONString(baseResponse));
        out.flush();
    }
}
