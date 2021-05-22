package org.zy.dev.cloud.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * fileName: SendEmailTo
 * create: 2021-5-22 12:20
 * description: 发送邮件信息
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailContent {
    /**
     * 发件人
     */
    private String from;
    
    /**
     * 收件人
     */
    private String[] to;
    
    /**
     * 抄送人
     */
    private String[] cc;
    
    /**
     * 密件抄送
     */
    private String[] bcc;
    
    /**
     * 发送日期
     */
    private Date sentDate;
    
    /**
     * 邮件标题
     */
    private String subject;
    
    /**
     * 邮件内容
     */
    private String text;
}
