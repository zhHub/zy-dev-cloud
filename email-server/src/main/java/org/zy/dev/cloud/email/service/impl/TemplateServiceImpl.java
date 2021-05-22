package org.zy.dev.cloud.email.service.impl;

import org.springframework.stereotype.Service;
import org.zy.dev.cloud.email.service.TemplateService;

/**
 * fileName: TemplateServiceImpl
 * create: 2021-5-22 22:06
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    /**
     * 通过模板标识 code 获取 HTML 模板
     *
     * @param code 模板标识
     * @return HTML 模板
     */
    @Override
    public String getTemplateByCode(String code) {
        // TODO: [张建元 18143774515@163.com 2021-5-22 23:28] 完善获取模板方法
        return "";
    }
}
