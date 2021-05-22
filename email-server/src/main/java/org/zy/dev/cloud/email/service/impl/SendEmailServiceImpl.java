package org.zy.dev.cloud.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.zy.dev.cloud.email.domain.SendEmailContent;
import org.zy.dev.cloud.email.domain.SendEmailUser;
import org.zy.dev.cloud.email.helper.EmailHelper;
import org.zy.dev.cloud.email.service.SendEmailService;
import org.zy.dev.cloud.email.service.SendEmailUserService;

import javax.mail.internet.MimeMessage;

/**
 * fileName: SendEmailServiceImpl
 * create: 2021-5-22 12:13
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {
    /**
     * bean
     */
    private final JavaMailSender javaMailSender;
    private final EmailHelper emailHelper;
    private final SendEmailUserService sendEmailUserService;
    
    @Autowired
    public SendEmailServiceImpl(JavaMailSender javaMailSender,
                                EmailHelper emailHelper,
                                SendEmailUserService sendEmailUserService) {
        this.javaMailSender = javaMailSender;
        this.emailHelper = emailHelper;
        this.sendEmailUserService = sendEmailUserService;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    @Override
    public boolean sendSimpleEmail(SendEmailContent sendEmailContent) {
        SendEmailUser sendEmailUser = sendEmailUserService.getSendEmailUserByUserName(sendEmailContent.getFrom());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sendEmailUser.getUsername());
        simpleMailMessage.setTo(sendEmailContent.getTo());
        simpleMailMessage.setCc(sendEmailContent.getCc());
        simpleMailMessage.setBcc(sendEmailContent.getBcc());
        simpleMailMessage.setSubject(sendEmailContent.getSubject());
        simpleMailMessage.setText(sendEmailContent.getText());
        return emailHelper.sendEmail(sendEmailUser, simpleMailMessage);
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    @Override
    public boolean sendMimeMessageEmail(SendEmailContent sendEmailContent) {
        SendEmailUser sendEmailUser = sendEmailUserService.getSendEmailUserByUserName(sendEmailContent.getFrom());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        return emailHelper.sendEmail(sendEmailUser, mimeMessage);
    }
}
