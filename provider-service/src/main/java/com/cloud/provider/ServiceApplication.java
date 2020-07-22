package com.cloud.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘志强
 * @date 2020/7/16 13:21
 * @EnableEurekaClient 开启Eureka 客户端
 */
@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class ServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

    @Override
    public void run(String... args) {
        log.info("provider服务器已启动");
    }
}
