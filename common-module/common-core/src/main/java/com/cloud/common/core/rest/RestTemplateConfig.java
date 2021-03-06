package com.cloud.common.core.rest;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 开发公司：XX公司
 * 版权：XX公司
 * <p>
 * RestTemplateConfig
 *
 * @author 刘志强
 * @created Create Time: 2019/3/25
 */

@Configuration
public class RestTemplateConfig {
    @Bean(name = "restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean(name = "factory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }
}