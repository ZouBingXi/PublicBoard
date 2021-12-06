package cn.szuer.publicboard.service;

import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.UserTypeMapper;
import cn.szuer.publicboard.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 自定义UserDetailsService，根据传入的userid，返回一个UserDetails
     * UserDetails属性包括账号，密码，用户身份
     * @param userid
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException
    {
        //根据userid查找用户
        UserInfo user=userInfoMapper.selectByPrimaryKey(Integer.parseInt(userid));
        System.out.println("User="+user);
        if (user==null)
        {
            throw new UsernameNotFoundException("用户不存在,请检查用户名");
        }

        //获取用户身份
        List<GrantedAuthority> authorities=new ArrayList<>();
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority
                ("ROLE_"+userTypeMapper.selectByPrimaryKey(user.getTypeid()).getTypename());
        authorities.add(grantedAuthority);

        return new User(String.valueOf(user.getUserid()),"{noop}"+user.getPassword(),authorities);
    }
}
