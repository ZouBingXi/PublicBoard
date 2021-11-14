package cn.szuer.publicboard.Interceptor;

import cn.szuer.publicboard.model.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception
    {
        HttpSession session=request.getSession();
        UserInfo user=(UserInfo)session.getAttribute("UserInfo");
        if (user==null)
        {
            return false;
        }
        return true;
    }
}
