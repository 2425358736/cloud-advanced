package com.cloud.consumer.rpc.impl;

import com.cloud.common.core.result.AjaxResult;
import com.cloud.common.core.result.HttpStatus;
import com.cloud.consumer.rpc.RpcProviderService;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 14:38
 */
@Component
public class RpcProviderImpl implements RpcProviderService {
    @Override
    public AjaxResult<Map<String,String>> getUserInfo(String authorization) {
        return AjaxResult.error(HttpStatus.ERROR, "下游服务器调用失败");
    }
}
