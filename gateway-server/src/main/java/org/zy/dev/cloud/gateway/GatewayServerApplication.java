package org.zy.dev.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * fileName: GatewayServerApplication
 * create: 2021-5-12 22:30:11
 * description: gateway server
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
