package cn.szuer.publicboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    @Qualifier("MyUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    AuthenticationFailureHandler failureHandler;

    /**
     * 指定加密方式
     * @return
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }*/

    /**
     * 配置自定义的UserDetailsService和加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
            //.passwordEncoder(passwordEncoder())
    }

    /**
     * 配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/user/login","/user/add","/user/all") //添加不需要登录即可访问的请求路径
                .permitAll()
                .anyRequest().authenticated()   //启动认证
                .and()
                .formLogin()
                //.loginPage("/login")  //自定义登录界面
                //.loginProcessingUrl("/user/login")    //自定义登录请求路径
                .usernameParameter("userid")    //自定义登录账号参数名(默认为username,密码默认为password)
                .successHandler(successHandler) //自定义登录成功处理类
                .failureHandler(failureHandler) //自定义登录失败处理类
                .permitAll()
                .and()
                .csrf().disable();  //不启用csrf访问

    }
}
