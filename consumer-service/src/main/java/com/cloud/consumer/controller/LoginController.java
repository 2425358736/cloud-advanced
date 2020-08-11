package com.cloud.consumer.controller;

import com.cloud.consumer.config.security.model.User;
import com.cloud.consumer.config.security.UserDetailsServiceImpl;
import com.cloud.common.core.result.AjaxResult;
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
    public AjaxResult login(@NotNull(message = "用户名不能为null") String userName, @NotNull(message = "密码不能为null") String password){
        User user = userDetailsService.loadUserByUsername(userName);
        if (StringUtils.equals(user.getPassword(),password)) {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisTemplate.opsForValue().set(token,user);
            AjaxResult ajaxResult = AjaxResult.successData(token);
            return ajaxResult;
        } else {
            return AjaxResult.error("密码错误");
        }
    }

    @GetMapping("/admin/test1")
    private AjaxResult test1() {
        return AjaxResult.success("test1");
    }

    @GetMapping("/user/test2")
    private AjaxResult test2() {
        return AjaxResult.success("test2");
    }

    @GetMapping("/member/test3")
    @PreAuthorize("hasAnyAuthority('member')")
    private AjaxResult test3() {
        return AjaxResult.success("test3");
    }

    @GetMapping("/member/test4")
    @PreAuthorize("hasAnyAuthority('admin')")
    private AjaxResult test4() {
        return AjaxResult.success("test4");
    }


}
