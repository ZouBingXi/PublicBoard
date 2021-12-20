package cn.szuer.publicboard.config;

import cn.szuer.publicboard.config.sessionStrategy.MyConcurrentSessionControlAuthenticationStrategy;
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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

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

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    PasswordEncoder encoder;

    /**
     * 指定加密方式
     * @return
     */
     @Bean
     public PasswordEncoder passwordEncoder()
     {
        return new BCryptPasswordEncoder();
     }

    /**
     * 配置自定义的UserDetailsService和加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
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
                .antMatchers("/","/user/add","/user/all","/user/sendcode") //添加不需要登录即可访问的请求路径
                .permitAll()
                .anyRequest().authenticated()   //启动认证
                .and()
                .addFilterAt(loginAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new ConcurrentSessionFilter(sessionRegistry,sessionInformationExpiredStrategy),ConcurrentSessionFilter.class)
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint) //用户未登录处理
                .accessDeniedHandler(accessDeniedHandler)   //用户没有权限处理
                .and()
                .csrf().disable();  //不启用csrf访问
    }

    LoginAuthenticationFilter loginAuthenticationFilter() throws Exception
    {
        LoginAuthenticationFilter loginAuthenticationFilter=new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginAuthenticationFilter.setUsernameParameter("userid");
        loginAuthenticationFilter.setFilterProcessesUrl("/user/login");
        loginAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        loginAuthenticationFilter.setSessionRegistry(sessionRegistry);
        return loginAuthenticationFilter;
    }

    /**
     * 在 Spring Security 中，它是通过监听 session 的销毁事件，来及时的清理 session 的记录。
     * 用户从不同的浏览器登录后，都会有对应的 session，当用户注销登录之后，session 就会失效，
     * 但是默认的失效是通过调用 StandardSession#invalidate 方法来实现的，这一个失效事件无法被 Spring 容器感知到，
     * 进而导致当用户注销登录之后，Spring Security 没有及时清理会话信息表，以为用户还在线，进而导致用户无法重新登录进来
     * 为了解决这一问题，我们提供一个 HttpSessionEventPublisher ，
     * 这个类实现了 HttpSessionListener 接口，在该 Bean 中，可以将 session 创建以及销毁的事件及时感知到，
     * 并且调用 Spring 中的事件机制将相关的创建和销毁事件发布出去，进而被 Spring Security 感知到
     * @return
     */
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher()
    {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry()
    {
        return new SessionRegistryImpl();
    }

    SessionAuthenticationStrategy sessionAuthenticationStrategy()
    {
        ConcurrentSessionControlAuthenticationStrategy sessionAuthenticationStrategy=new MyConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        sessionAuthenticationStrategy.setMaximumSessions(1);    //最大同时登录数
        return sessionAuthenticationStrategy;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/index.html","/favicon.ico","/fonts/**");
    }
}
