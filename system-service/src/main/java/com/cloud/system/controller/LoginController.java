package com.cloud.system.controller;

import com.cloud.common.core.domain.AuthToken;
import com.cloud.common.core.result.AjaxResult;
import com.cloud.system.rpc.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/8/11 15:44
 */

@RestController
@Slf4j
@CrossOrigin
public class LoginController {

    private final AuthService authService;

    private final RestTemplate restTemplate;

    public LoginController(AuthService authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public Object login(String userName, String password) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Basic Y29uc3VtZXI6MTIzNDU2");
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("username",userName);
        map.add("password",password);
        map.add("grant_type","password");
        HttpEntity requestEntity = new HttpEntity<>(map, requestHeaders);
        ResponseEntity<Object> entity = restTemplate.exchange("http://auth-service/oauth/token", HttpMethod.POST,requestEntity,Object.class);
        return entity.getBody();
    }
}
