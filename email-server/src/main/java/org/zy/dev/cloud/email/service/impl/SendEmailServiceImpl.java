package org.zy.dev.cloud.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.zy.dev.cloud.email.constant.EmailConstants;
import org.zy.dev.cloud.email.domain.SendEmailContent;
import org.zy.dev.cloud.email.domain.SendEmailUser;
import org.zy.dev.cloud.email.helper.EmailHelper;
import org.zy.dev.cloud.email.service.TemplateService;
import org.zy.dev.cloud.email.service.SendEmailService;
import org.zy.dev.cloud.email.service.SendEmailUserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        // 设置邮件内容
        simpleMailMessage.setText(sendEmailContent.getText());
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
            // 设置邮件内容
            mimeMessageHelper.setText(sendEmailContent.getTemplateFlag() ?
                            // 是模板邮件，组装模板邮件内容
                            this.assemblyTemplateText(sendEmailContent.getTemplateCode(),
                                    sendEmailContent.getTemplateText()) :
                            // 不是模板邮件，直接取邮件内容
                            sendEmailContent.getText(),
                    // true 表示开启 html 格式的内容
                    true);
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
    
    /**
     * 组装模板邮件内容
     *
     * @param templateCode 模板标识
     * @param templateText 模板替换内容
     * @return 模板邮件内容
     */
    private String assemblyTemplateText(String templateCode, HashMap<String, String> templateText) {
        String template = templateService.getTemplateByCode(templateCode);
        Assert.notNull(template, "模板不存在或模板已删除");
        for (Map.Entry<String, String> entry : templateText.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // TODO: [张建元 18143774515@163.com 2021-5-22 22:58] 模板只考虑了（k，v）一一对应的关系，
            //  未考虑存在遍历 table 或列表的情况，后续需要改进此处代码使模板功能更加通用
            template = template.replace("${" + key + "}", value);
        }
        return template;
    }
}
