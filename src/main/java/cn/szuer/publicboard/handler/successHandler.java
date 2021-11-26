package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverterImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * 用于处理登录请求成功后的类
 * 将返回类信息写入响应体中
 */
@Component
public class successHandler implements AuthenticationSuccessHandler
{
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException
    {
        //设置返回类型
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out=response.getWriter();

        //获取用户信息
        BaseResponse<UserDto> baseResponse=new BaseResponse<>(200,"登录成功");
        User user=(User)authentication.getPrincipal();
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(Integer.parseInt(user.getUsername()));

        //将UserInfo转为UserDto写入返回类中
        if (userInfo!=null)
        {
            UserDto userDto=UserConverterImpl.INSTANCE.UserInfo2UserDto(userInfo);
            baseResponse.setData(userDto);
        }

        //将返回信息写入响应体中
        String res=JSON.toJSONString(baseResponse);
        out.print(res);
    }
}
