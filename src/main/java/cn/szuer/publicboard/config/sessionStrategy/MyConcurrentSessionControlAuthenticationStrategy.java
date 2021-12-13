package cn.szuer.publicboard.config.sessionStrategy;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 自定义处理session的类
 * 重写踢掉最老的session方法
 */
public class MyConcurrentSessionControlAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy
{
    public MyConcurrentSessionControlAuthenticationStrategy(SessionRegistry sessionRegistry)
    {
        super(sessionRegistry);
    }

    @Override
    protected void allowableSessionsExceeded(List<SessionInformation> sessions,int allowableSessions,SessionRegistry registry) throws SessionAuthenticationException
    {
        if (sessions!=null)
        {
            //根据时间进行排序
            sessions.sort(Comparator.comparing(SessionInformation::getLastRequest));

            //筛选出要踢掉的session
            int maximumSessionsExceededBy=sessions.size()-allowableSessions;
            List<SessionInformation> sessionsToBeExpired=sessions.subList(0,maximumSessionsExceededBy);

            Iterator var6=sessionsToBeExpired.iterator();
            while (var6.hasNext())
            {
                SessionInformation session=(SessionInformation)var6.next();
                session.expireNow();    //将session设为过期
            }

        }
        else
        {
            throw new SessionAuthenticationException(this.messages.getMessage("ConcurrentSessionControlAuthenticationStrategy.exceededAllowed",
                                                                              new Object[]{allowableSessions},
                                                                              "Maximum sessions of {0} for this principal exceeded"));
        }
    }
}
