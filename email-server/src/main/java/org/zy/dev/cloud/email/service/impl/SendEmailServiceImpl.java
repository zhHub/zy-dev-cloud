package org.zy.dev.cloud.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.zy.dev.cloud.email.constant.EmailConstants;
import org.zy.dev.cloud.email.domain.SendEmailContent;
import org.zy.dev.cloud.email.domain.SendEmailUser;
import org.zy.dev.cloud.email.helper.EmailHelper;
import org.zy.dev.cloud.email.service.SendEmailService;
import org.zy.dev.cloud.email.service.SendEmailUserService;
import org.zy.dev.cloud.email.service.TemplateService;
import org.zy.dev.cloud.email.util.EmailUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SendEmailServiceImpl.class);
    /**
     * bean
     */
    private final JavaMailSender javaMailSender;
    private final EmailHelper emailHelper;
    private final SendEmailUserService sendEmailUserService;
    private final TemplateService templateService;
    
    @Autowired
    public SendEmailServiceImpl(JavaMailSender javaMailSender,
                                EmailHelper emailHelper,
                                SendEmailUserService sendEmailUserService,
                                TemplateService templateService) {
        this.javaMailSender = javaMailSender;
        this.emailHelper = emailHelper;
        this.sendEmailUserService = sendEmailUserService;
        this.templateService = templateService;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    @Override
    public boolean sendSimpleEmail(SendEmailContent sendEmailContent) {
        // 获取发件人信息
        SendEmailUser sendEmailUser = sendEmailUserService.getSendEmailUserByUserName(sendEmailContent.getFrom());
        // 构造邮件消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置发件人
        simpleMailMessage.setFrom(sendEmailUser.getUsername());
        // 设置收件人
        simpleMailMessage.setTo(sendEmailContent.getTo());
        // 设置抄送人
        simpleMailMessage.setCc(sendEmailContent.getCc());
        // 设置密件抄送人
        simpleMailMessage.setBcc(sendEmailContent.getBcc());
        // 设置邮件主题
        simpleMailMessage.setSubject(sendEmailContent.getSubject());
        // 获取邮件内容
        String text = sendEmailContent.getTemplateFlag() ?
                // 是模板邮件，获取模板，组装模板邮件内容
                EmailUtils.assemblyTemplateText(
                        templateService.getTemplateByCode(sendEmailContent.getTemplateCode()),
                        sendEmailContent.getTemplateText()) :
                // 不是模板邮件，直接取邮件内容
                sendEmailContent.getText();
        // 设置邮件内容
        simpleMailMessage.setText(text);
        // 发送邮件
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
        // 获取发件人信息
        SendEmailUser sendEmailUser = sendEmailUserService.getSendEmailUserByUserName(sendEmailContent.getFrom());
        // 构造邮件消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            // 使用 MimeMessageHelper 提供对HTML文本内容，图像等内联元素和典型邮件附件的支持
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置发件人
            mimeMessageHelper.setFrom(sendEmailUser.getUsername());
            // 设置收件人
            mimeMessageHelper.setTo(sendEmailContent.getTo());
            // 设置抄送人
            mimeMessageHelper.setCc(sendEmailContent.getCc());
            // 设置密件抄送人
            mimeMessageHelper.setBcc(sendEmailContent.getBcc());
            // 设置邮件主题
            mimeMessageHelper.setSubject(sendEmailContent.getSubject());
            // 获取邮件内容
            String text = sendEmailContent.getTemplateFlag() ?
                    // 是模板邮件，获取模板，组装模板邮件内容
                    EmailUtils.assemblyTemplateText(
                            templateService.getTemplateByCode(sendEmailContent.getTemplateCode()),
                            sendEmailContent.getTemplateText()) :
                    // 不是模板邮件，直接取邮件内容
                    sendEmailContent.getText();
            // 设置邮件内容，默认开启 html 格式的内容
            mimeMessageHelper.setText(text, sendEmailContent.getHtmlFlag());
            // 设置邮件附件
            String[] attachmentPath = sendEmailContent.getAttachmentPath();
            if (attachmentPath != null && attachmentPath.length > 0) {
                // 存在附件，遍历所有附件
                for (String path : attachmentPath) {
                    // 读取附件
                    FileSystemResource file = new FileSystemResource(path);
                    // 截取附件名
                    String fileName = path.substring(path.lastIndexOf(File.separator));
                    // 添加所有附件至邮件
                    mimeMessageHelper.addAttachment(fileName, file);
                }
            }
            // 发送邮件，返回发送结果
            return emailHelper.sendEmail(sendEmailUser, mimeMessage);
        } catch (MessagingException e) {
            // 构建邮件消息异常，返回发送失败
            LOG.error(EmailConstants.FAIL_MESSAGE + e.getMessage());
            return false;
        }
    }
}
