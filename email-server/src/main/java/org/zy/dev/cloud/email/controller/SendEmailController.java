package org.zy.dev.cloud.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zy.dev.cloud.email.domain.SendEmailContent;
import org.zy.dev.cloud.email.service.SendEmailService;

/**
 * fileName: SendEmailController
 * create: 2021-5-22 12:53
 * description: 发送邮件接口
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@RestController
@RequestMapping("/emails")
public class SendEmailController {
    
    private final SendEmailService sendEmailService;
    
    @Autowired
    public SendEmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
    
    /**
     * 发送邮件
     *
     * @param sendEmailContent 发送邮件信息
     * @return 发送结果
     */
    @PostMapping("/simple")
    public boolean sendMessage(@RequestBody SendEmailContent sendEmailContent) {
        return sendEmailService.sendMimeMessageEmail(sendEmailContent);
    }
}
