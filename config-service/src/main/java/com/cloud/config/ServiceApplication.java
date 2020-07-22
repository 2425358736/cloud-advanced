package com.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘志强
 * @date 2020/7/16 13:21
 * @EnableEurekaClient 开启Eureka 客户端
 * @EnableFeignClients 开始Feign客户端
 */
@SpringBootApplication(scanBasePackages = {"com.cloud.config"} )
@Slf4j
@EnableEurekaClient
@EnableConfigServer
public class ServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

    @Override
    public void run(String... args) {
        log.info("consumer服务器已启动");
    }
}
