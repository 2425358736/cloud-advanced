package com.cloud.auth.config.aop;

import com.cloud.auth.config.auth.exception.CustomOauthException;
import com.cloud.common.core.domain.AuthToken;
import com.cloud.common.core.result.AjaxResult;
import org.apache.catalina.connector.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 开发公司：个人
 * 版权：个人
 * <p>
 * AuthTokenAspect
 *
 * @author 刘志强
 * @created Create Time: 2020/8/12
 */
@Component
@Aspect
public class AuthTokenAspect {

    /**
     *  @Around注解 改变controller返回值的
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Response response = new Response();
        Object proceed = pjp.proceed();
        if (proceed != null) {
            ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>) proceed;
            OAuth2AccessToken body = responseEntity.getBody();
            // 返回token值
            AuthToken accessToken = new AuthToken();
            accessToken.setAccess_token(body.getValue());
            accessToken.setExpires_in(String.valueOf(body.getExpiresIn()));
            accessToken.setRefresh_token(body.getRefreshToken().getValue());
            return ResponseEntity.status(200).body(AjaxResult.successData(accessToken));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}