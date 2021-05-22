package org.zy.dev.cloud.email.service;

import org.zy.dev.cloud.email.domain.SendEmailUser;

/**
 * fileName: SendEmailUserService
 * create: 2021-5-22 14:47
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
public interface SendEmailUserService {
    
    /**
     * 获取发件人信息
     *
     * @param userName 用户名
     * @return 发件人信息
     */
    SendEmailUser getSendEmailUserByUserName(String userName);
}
