package com.cloud.system.rpc;


import com.cloud.common.core.domain.AuthToken;
import com.cloud.common.core.result.AjaxResult;
import com.cloud.system.rpc.impl.AuthServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author admin
 */
@FeignClient(value = "auth-service", fallback = AuthServiceImpl.class)
public interface AuthService {

    /**
     * 换取token
     * @param authorization
     * @param userName
     * @param password
     * @param grant_type
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token")
    String getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("grant_type") String grant_type);
}