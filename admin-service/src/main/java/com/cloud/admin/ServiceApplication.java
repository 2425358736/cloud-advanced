package com.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘志强
 * @date 2020/7/16 13:21
 * @EnableEurekaClient 开启Eureka 客户端
 * @EnableFeignClients 开始Feign客户端
 */
@SpringBootApplication
@Slf4j
@EnableAdminServer
@EnableEurekaClient
public class ServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

    @Override
    public void run(String... args) {
        log.info("admin服务器已启动");
    }
}
