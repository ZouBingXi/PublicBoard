package cn.szuer.publicboard.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
public class MailUtil
{
    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String from;

    public void send(String to,String subject,String text) throws MessagingException
    {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSentDate(new Date());
        helper.setText(text,true);
        mailSender.send(mimeMessage);
    }

    public int verify(HttpSession session,String email,String code)
    {
        if (session.getAttribute("code")==null)
            return 1;
        //不是邮箱验证时的邮箱
        if (!session.getAttribute("email").equals(email))
            return 2;
        //验证码超时
        Date lastTime=(Date)session.getAttribute("codeTime");
        if (new Date().getTime()-lastTime.getTime()>1000*60*5)
            return 3;
        //验证码错误
        if (!session.getAttribute("code").equals(code))
            return 4;
        return 0;
    }
}
