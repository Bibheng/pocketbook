package com.matthew.pocketbook.common.util;

import com.matthew.pocketbook.common.entity.MailInfo;
import com.matthew.pocketbook.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 邮件工具类
 *
 * @author Matthew
 * @date 2021-02-23 11:04
 **/
@Component
@Slf4j
public class MailUtil {
    private static JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        MailUtil.mailSender = mailSender;
    }

    /**
     * 发件人
     */
    private static String from;

    @Value("${spring.mail.username}")
    public void setFrom(String from) {
        MailUtil.from = from;
    }

    /**
     * 发送邮件
     *
     * @param info 邮件内容对象
     * @param isHtml 邮件内容是否为html
     * @author Matthew
     * @date 2021-02-23 11:35
     */
    public static void sendMail(MailInfo info, boolean isHtml) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage());
            helper.setFrom(from);
            helper.setTo(info.getReceiver());
            helper.setSubject(info.getSubject());
            helper.setText(info.getContent(), isHtml);
            mailSender.send(helper.getMimeMessage());
        } catch (Exception e) {
            throw new CustomException("发送邮件失败:" + e.getMessage(), e);
        }
    }

    public static void sendTextMail(MailInfo info) {
        sendMail(info, false);
    }

}
