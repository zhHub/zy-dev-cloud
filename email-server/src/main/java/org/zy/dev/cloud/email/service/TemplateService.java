package org.zy.dev.cloud.email.service;

/**
 * fileName: TemplateService
 * create: 2021-5-22 22:04
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
public interface TemplateService {
    /**
     * 通过模板标识 code 获取 HTML 模板
     *
     * @param code 模板标识
     * @return HTML 模板
     */
    String getTemplateByCode(String code);
}
