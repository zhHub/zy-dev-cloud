package org.zy.dev.cloud.email.service.impl;

import org.springframework.stereotype.Service;
import org.zy.dev.cloud.email.constant.MailboxTypeEnum;
import org.zy.dev.cloud.email.domain.SendEmailUser;
import org.zy.dev.cloud.email.service.SendEmailUserService;

/**
 * fileName: SendEmailUserServiceImpl
 * create: 2021-5-22 14:49
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Service
public class SendEmailUserServiceImpl implements SendEmailUserService {
    private static final String USERNAME = "18143774515@163.com";
    private static final String PASSWORD = "xxxxxx";
    /**
     * 获取发件人信息
     *
     * @param userName 用户名
     * @return 发件人信息
     */
    @Override
    public SendEmailUser getSendEmailUserByUserName(String userName) {
        return SendEmailUser.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .mailboxType(MailboxTypeEnum.EMAIL_163).build();
    }
}
