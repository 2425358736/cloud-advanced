package com.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘志强
 * @date 2020/7/28 18:18
 */
@RestController
public class FallbackController {

    @RequestMapping("/defaultFallback")
    public String fallback() {
        return "服务器发生hystrix降级处理";
    }
}
