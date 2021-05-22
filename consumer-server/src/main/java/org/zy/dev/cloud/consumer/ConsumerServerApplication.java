package org.zy.dev.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * fileName: GatewayServerApplication
 * create: 2021-5-12 22:30:11
 * description: gateway server
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServerApplication.class, args);
	}

}
