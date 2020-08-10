package com.cloud.consumer.controller;

import com.cloud.consumer.config.security.model.User;
import com.cloud.consumer.config.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 刘志强
 * @date 2020/7/21 10:53
 */
@RestController
@RequestMapping("/")
@CrossOrigin
@Slf4j
public class LoginController {

    private final UserDetailsServiceImpl userDetailsService;

    private final RedisTemplate redisTemplate;

    public LoginController(UserDetailsServiceImpl userDetailsService, @Qualifier("redisTemplateJdk") RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
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
//            TokenMap.getTokenMap().getMap().put(token, user);
            redisTemplate.opsForValue().set(token,user);
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

    @GetMapping("/user/aa")
    private String userAA() {
        return ":aaa";
    }

    @GetMapping("/member/aa")
    @PreAuthorize("hasAnyAuthority('member')")
    private String bb() {
        return ":aa";
    }

    @GetMapping("/member/bb")
    @PreAuthorize("hasAnyAuthority('admin')")
    private String cc() {
        return ":bb";
    }


}
