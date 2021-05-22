package org.zy.dev.cloud.email.util;

import java.util.Map;

/**
 * fileName: EmailUtils
 * create: 2021-5-22 23:41
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
public class EmailUtils {
    /**
     * 空构造器
     */
    EmailUtils() {
    }
    
    /**
     * 组装模板邮件内容
     *
     * @param template     模板
     * @param templateText 模板替换内容
     * @return 模板邮件内容
     */
    public static String assemblyTemplateText(String template, Map<String, String> templateText) {
        for (Map.Entry<String, String> entry : templateText.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // TODO: [张建元 18143774515@163.com 2021-5-22 22:58] 模板只考虑了（k，v）一一对应的关系，
            //  未考虑存在遍历 table 或列表的情况，后续需要改进此处代码使模板功能更加通用
            template = template.replace("${" + key + "}", value);
        }
        return template;
    }
}
