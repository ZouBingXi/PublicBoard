package cn.szuer.publicboard.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * Spring Security提供的过滤器不支持接受json格式的参数
 * 自定义过滤器继承UsernamePasswordAuthenticationFilter
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private SessionRegistry sessionRegistry;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException
    {
        //只接受POST请求
        if (!request.getMethod().equals("POST"))
        {
            throw new AuthenticationServiceException("Authentication method not supported: "+request.getMethod());
        }
        else
        {
            //如果参数为json格式,则转化
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE))
            {
                String userid="";
                String password="";

                try
                {
                    //接受request里面的json字符串并转为userid和password
                    String body=getBody(request);
                    Map map=JSON.parseObject(body);
                    userid=String.valueOf(map.get(this.getUsernameParameter()));
                    password=(String)map.get(this.getPasswordParameter());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                userid=userid.trim();

                //封装成token提交给AuthenticationManager进行认证
                UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken(userid,password);
                sessionRegistry.registerNewSession(request.getSession().getId(),authRequest.getPrincipal());
                setDetails(request,authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
            else
            {
                //如果参数为表单格式,则使用框架提供的方法验证
                return super.attemptAuthentication(request, response);
            }
        }
    }

    private String getBody(HttpServletRequest request) throws IOException
    {
        BufferedReader reader=request.getReader();

        StringBuilder sb=new StringBuilder();
        String str=null;
        while ((str=reader.readLine())!=null)
        {
            sb.append(str);
        }

        return sb.toString();
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry)
    {
        this.sessionRegistry=sessionRegistry;
    }
}
