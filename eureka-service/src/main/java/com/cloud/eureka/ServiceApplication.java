package com.cloud.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 刘志强
 * @date 2020/7/16 13:21
 * @EnableEurekaServer 开启Eureka 服务端
 */
@SpringBootApplication
@Slf4j
@EnableEurekaServer
public class ServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

    @Override
    public void run(String... args) {
        log.info("Eureka服务器已启动");
    }
}
