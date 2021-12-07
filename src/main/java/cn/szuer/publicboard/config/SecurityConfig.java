package cn.szuer.publicboard.config;

import cn.szuer.publicboard.filter.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.Filter;

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

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;


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
                .antMatchers("/","/user/add","/user/all") //添加不需要登录即可访问的请求路径
                .permitAll()
                .anyRequest().authenticated()   //启动认证
                .and()
                .addFilterAt(loginAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/#/login")  //自定义登录界面
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .csrf().disable();  //不启用csrf访问
    }

    @Bean
    LoginAuthenticationFilter loginAuthenticationFilter() throws Exception
    {
        LoginAuthenticationFilter loginAuthenticationFilter=new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginAuthenticationFilter.setUsernameParameter("userid");
        loginAuthenticationFilter.setFilterProcessesUrl("/user/login");
        return loginAuthenticationFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/index.html","/favicon.ico","/fonts/**");
    }
}
