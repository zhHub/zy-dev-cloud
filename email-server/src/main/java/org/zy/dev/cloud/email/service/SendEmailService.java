package org.zy.dev.cloud.email.service;

import org.zy.dev.cloud.email.domain.SendEmailContent;

/**
 * fileName: SendEmailService
 * create: 2021-5-22 12:13
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
public interface SendEmailService {
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    boolean sendSimpleEmail(SendEmailContent sendEmailContent);
    
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    boolean sendMimeMessageEmail(SendEmailContent sendEmailContent);
}
