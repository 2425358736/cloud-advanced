package com.cloud.consumer.controller;

import com.cloud.common.core.result.AjaxResult;
import com.cloud.consumer.rpc.RpcProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public AjaxResult<Map<String,String>> getConsumer() {
        String authorization = httpServletRequest.getHeader("Authorization");
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Authorization", "123456");
//        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
//        ResponseEntity<AjaxResult> entity = restTemplate.exchange("http://provider-service/rpc/getUserInfo", HttpMethod.GET,requestEntity,AjaxResult.class);
//        log.info(JSONObject.toJSONString(entity.getBody()));
        return rpcProviderService.getUserInfo(authorization);
    }

    @GetMapping("/admin/test1")
    public AjaxResult test1() {
        return AjaxResult.success("test1");
    }

    @GetMapping("/user/test2")
    public AjaxResult test2() {
        return AjaxResult.success("test2");
    }

    @GetMapping("/testPer1")
    @PreAuthorize("hasAnyAuthority('member')")
    public AjaxResult testPer1() {
        return AjaxResult.success("test3");
    }

    @GetMapping("/testPer2")
    @PreAuthorize("hasAnyAuthority('admin')")
    public AjaxResult testPer2() {
        return AjaxResult.success("test4");
    }


}
