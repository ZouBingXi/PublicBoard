package cn.szuer.publicboard.Interceptor;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
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
        UserDto user=(UserDto)session.getAttribute("UserDto");
        if (user==null)
        {
            //UTF-8编码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            BaseResponse baseResponse=new BaseResponse(400,"用户未登录,请先登录");
            String json=JSON.toJSONString(baseResponse);
            response.getWriter().print(json);
            return false;
        }
        return true;
    }
}
