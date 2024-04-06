package com.hoanghuy2209.borrowservice;

import com.hoanghuy2209.borrowservice.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({AxonConfig.class})
public class BorrowserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowserviceApplication.class, args);
	}

}
