package com.cloud.provider.service.impl;

import com.cloud.common.core.result.AjaxResult;
import com.cloud.provider.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 15:38
 */
@Service
public class UserImpl implements UserService {
    private final HttpServletRequest httpServletRequest;

    public UserImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public AjaxResult getUserInfo() {
        Map<String,String> map = new HashMap<>(3);
        String authorization = httpServletRequest.getHeader("Authorization");
        map.put("authorization",authorization);
        map.put("state","200");
        map.put("作者","刘志强");
        return AjaxResult.successData(map);
    }
}
