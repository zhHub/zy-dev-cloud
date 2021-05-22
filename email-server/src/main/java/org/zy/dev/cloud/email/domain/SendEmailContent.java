package org.zy.dev.cloud.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

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
    
    /**
     * 是否使用模板，默认否
     */
    private Boolean templateFlag = Boolean.FALSE;
    
    /**
     * 是否使用默认内容类型（“text/plain”）为HTML邮件应用内容类型 “text/html”,默认是
     */
    private Boolean htmlFlag = Boolean.TRUE;
    
    /**
     * HTML 模板标识 code
     */
    private String templateCode;
    
    /**
     * 模板内容
     */
    private HashMap<String, String> templateText = new HashMap<>();
    
    /**
     * 附件
     */
    private String[] attachmentPath;
}
