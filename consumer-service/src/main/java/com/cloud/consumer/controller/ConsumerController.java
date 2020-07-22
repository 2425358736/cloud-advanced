package com.cloud.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer.rpc.RpcProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 14:34
 */
@RestController
@CrossOrigin
@Slf4j
public class ConsumerController {

    private final RpcProviderService rpcProviderService;

    private final HttpServletRequest httpServletRequest;

    private final RestTemplate restTemplate;

    public ConsumerController(RpcProviderService rpcProviderService, HttpServletRequest httpServletRequest, RestTemplate restTemplate) {
        this.rpcProviderService = rpcProviderService;
        this.httpServletRequest = httpServletRequest;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getConsumer")
    public Map<String,String> getConsumer() {
        String authorization = httpServletRequest.getHeader("Authorization");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "123456");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<Map> entity = restTemplate.exchange("http://provider-service/rpc/getUserInfo", HttpMethod.GET,requestEntity,Map.class);
        log.info(JSONObject.toJSONString(entity));
        return rpcProviderService.getUserInfo(authorization);
    }

}
