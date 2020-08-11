package com.cloud.system.rpc.impl;

import com.cloud.common.core.domain.AuthToken;
import com.cloud.system.rpc.AuthService;
import org.springframework.stereotype.Component;

/**
 * @author 刘志强
 * @date 2020/8/11 15:52
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthToken getToken(String authorization, String userName, String password, String grant_type) {
        return null;
    }

}
