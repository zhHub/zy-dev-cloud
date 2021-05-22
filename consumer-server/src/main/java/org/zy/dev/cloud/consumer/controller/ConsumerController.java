package org.zy.dev.cloud.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName: ConsumerController
 * create: 2021-5-12 23:03
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    
    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
