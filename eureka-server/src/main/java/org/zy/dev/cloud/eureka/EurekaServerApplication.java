package org.zy.dev.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * fileName: EurekaServerApplication
 * create: 2021-5-12 21:40:49
 * description: EurekaServer
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@EnableDiscoveryClient
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
    
}
