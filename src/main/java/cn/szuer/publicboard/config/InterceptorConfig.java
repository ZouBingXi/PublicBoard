package cn.szuer.publicboard.config;

import cn.szuer.publicboard.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/add","/user/test","/user/all");
    }
}
