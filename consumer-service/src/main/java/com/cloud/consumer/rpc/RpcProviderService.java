package com.cloud.consumer.rpc;

import com.cloud.consumer.rpc.impl.RpcProviderImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 14:36
 */
@FeignClient(value = "provider-service", fallback = RpcProviderImpl.class)
public interface RpcProviderService {

    /**
     * 测试
     * @param authorization
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rpc/getUserInfo")
    Map<String,String> getUserInfo(@RequestHeader(value = "Authorization") String authorization);
}
