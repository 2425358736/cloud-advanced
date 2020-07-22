package com.cloud.consumer.rpc.impl;

import com.cloud.consumer.rpc.RpcProviderService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 14:38
 */
@Component
public class RpcProviderImpl implements RpcProviderService {
    @Override
    public Map<String, String> getUserInfo(String authorization) {
        Map<String,String> map = new HashMap<>(2);
        map.put("state","500");
        map.put("msg","下游服务调用失败");
        return map;
    }
}
