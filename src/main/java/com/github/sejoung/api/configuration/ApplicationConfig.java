package com.github.sejoung.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author kim se joung
 *
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3 * 1000);
        factory.setReadTimeout(3 * 1000);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
}
