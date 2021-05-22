package org.zy.dev.cloud.email.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.zy.dev.cloud.email.constant.EmailConstants;
import org.zy.dev.cloud.email.domain.SendEmailUser;

import javax.mail.internet.MimeMessage;

/**
 * fileName: EmailHelper
 * create: 2021-5-22 14:21
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Component
public class EmailHelper {
    /**
     * 日志打印
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmailHelper.class);
    
    private static final String FAIL_MESSAGE = "邮件发送失败";
    private static final String SUCCESS_MESSAGE = "邮件发送成功";
    
    private final JavaMailSender mailSender;
    
    @Autowired
    public EmailHelper(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    /**
     * 根据发件人配置邮件发送客户端
     *
     * @param sendEmailUser 发件人
     * @return 邮件发送客户端
     */
    private synchronized JavaMailSenderImpl configurationJavaMailSenderImpl(SendEmailUser sendEmailUser) {
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
        switch (sendEmailUser.getMailboxType()) {
            case EMAIL_163:
                javaMailSender.setProtocol(EmailConstants.Email163.PROTOCOL);
                javaMailSender.setHost(EmailConstants.Email163.HOST);
                javaMailSender.setPort(EmailConstants.Email163.PORT);
                javaMailSender.setDefaultEncoding(EmailConstants.Email163.DEFAULT_ENCODING);
                break;
            case EMAIL_QQ:
                javaMailSender.setProtocol(EmailConstants.EmailTencent.PROTOCOL);
                javaMailSender.setHost(EmailConstants.EmailTencent.HOST);
                javaMailSender.setPort(EmailConstants.EmailTencent.PORT);
                javaMailSender.setDefaultEncoding(EmailConstants.EmailTencent.DEFAULT_ENCODING);
                break;
            default:
                break;
        }
        javaMailSender.setUsername(sendEmailUser.getUsername());
        javaMailSender.setPassword(sendEmailUser.getPassword());
        return javaMailSender;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser     发件人
     * @param simpleMailMessage 邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, SimpleMailMessage simpleMailMessage) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(simpleMailMessage);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser      发件人
     * @param simpleMailMessages 邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, SimpleMailMessage... simpleMailMessages) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(simpleMailMessages);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser 发件人
     * @param mimeMessage   邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, MimeMessage mimeMessage) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(mimeMessage);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser 发件人
     * @param mimeMessages  邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, MimeMessage... mimeMessages) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(mimeMessages);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser         发件人
     * @param mimeMessagePreparator 邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, MimeMessagePreparator mimeMessagePreparator) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(mimeMessagePreparator);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailUser          发件人
     * @param mimeMessagePreparators 邮件内容
     * @return 发送结果
     */
    public boolean sendEmail(SendEmailUser sendEmailUser, MimeMessagePreparator... mimeMessagePreparators) {
        try {
            configurationJavaMailSenderImpl(sendEmailUser).send(mimeMessagePreparators);
            LOG.info(SUCCESS_MESSAGE);
        } catch (MailException e) {
            LOG.error(FAIL_MESSAGE + e.getMessage());
            return false;
        }
        return true;
    }
}
