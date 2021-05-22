package org.zy.dev.cloud.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zy.dev.cloud.email.constant.MailboxTypeEnum;

/**
 * fileName: SendEmailUser
 * create: 2021-5-22 14:34
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailUser {
    /**
     * 用户名
     */
    String username;
    
    /**
     * 密码
     */
    String password;
    
    /**
     * 用户邮箱类型
     */
    MailboxTypeEnum mailboxType;
}
