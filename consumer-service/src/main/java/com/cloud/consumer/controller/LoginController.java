package com.cloud.consumer.controller;

import com.cloud.consumer.config.security.TokenMap;
import com.cloud.consumer.config.security.User;
import com.cloud.consumer.config.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 刘志强
 * @date 2020/7/21 10:53
 */
@RestController
@CrossOrigin
@Slf4j
public class LoginController {

    private final UserDetailsServiceImpl userDetailsService;

    public LoginController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Map<String,String> login(@NotNull(message = "用户名不能为null") String userName, @NotNull(message = "密码不能为null") String password){
        Map<String,String> map = new HashMap<>();
        User user = userDetailsService.loadUserByUsername(userName);
        if (StringUtils.equals(user.getPassword(),password)) {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            TokenMap.getTokenMap().getMap().put(token, user);
            map.put("state","200");
            map.put("token",token);
        } else {
            map.put("state","500");
        }
        return map;
    }

    @GetMapping("/admin/aa")
    private String aa() {
        return ":aaa";
    }
}
