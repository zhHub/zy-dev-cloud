package org.zy.dev.cloud.email.constant;

/**
 * fileName: EmailConstants
 * create: 2021-5-22 14:15
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
public interface EmailConstants {
    /**
     * 邮件发送失败
     */
    String FAIL_MESSAGE = "邮件发送失败, 失败原因：";
    /**
     * 邮件发送成功
     */
    String SUCCESS_MESSAGE = "邮件发送成功";
    
    /**
     * 163 邮箱配置
     */
    interface Email163 {
        /**
         * smtp服务主机
         */
        String HOST = "smtp.163.com";
        /**
         * 服务端口
         */
        Integer PORT = 465;
        /**
         * 服务协议
         */
        String PROTOCOL = "smtps";
        /**
         * 默认编码
         */
        String DEFAULT_ENCODING = "UTF-8";
    }
    
    /**
     * qq 邮箱配置
     */
    interface EmailTencent {
        /**
         * smtp服务主机
         */
        String HOST = "smtp.qq.com";
        /**
         * 服务端口
         */
        Integer PORT = 465;
        /**
         * 服务协议
         */
        String PROTOCOL = "smtps";
        /**
         * 默认编码
         */
        String DEFAULT_ENCODING = "UTF-8";
    }
}
