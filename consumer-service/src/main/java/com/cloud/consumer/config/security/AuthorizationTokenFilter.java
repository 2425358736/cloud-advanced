package com.cloud.consumer.config.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.common.core.result.AjaxResult;
import com.cloud.common.core.result.HttpStatus;
import com.cloud.consumer.config.security.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author admin
 */
@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    private final RedisTemplate redisTemplate;

    public AuthorizationTokenFilter(@Qualifier("redisTemplateJdk") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isNotEmpty(authorization) && SecurityContextHolder.getContext().getAuthentication() == null) {
            Object object =  redisTemplate.opsForValue().get(authorization);
            if (object != null) {
                User user = (User) object;
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
