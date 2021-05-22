package org.zy.dev.cloud.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * fileName: EmailServerApplication
 * create: 2021-5-22 11:10
 * description:
 * history:
 *
 * @author 张建元 18143774515@163.com
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EmailServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServerApplication.class, args);
	}

}
