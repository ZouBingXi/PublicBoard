package cn.szuer.publicboard.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * 获取当前登录用户个人信息的工具类
 */
@Component
public class AuthenticationUtil {

  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  /**
   * 获取当前登录用户的用户ID
   * @return
   */
  public Integer getAuthenticatedId() {
    return Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
  }
 
}
