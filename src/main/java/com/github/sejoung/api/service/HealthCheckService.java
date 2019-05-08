package com.github.sejoung.api.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.github.sejoung.api.model.ClickViewData;
import com.github.sejoung.api.model.ClickViewDataSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HealthCheckService {

    @Value("${telegram.url}")
    private String telegramUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void test() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<String> list = new ArrayList<String>();
        list.add("httpbin.org");

        for (String url : list) {
            try {
                ResponseEntity<String> response = restTemplate.exchange("http://" + url + "/get", HttpMethod.GET, entity, String.class);
                HttpStatus statusCode = response.getStatusCode();
                log.debug(url);
                if (!HttpStatus.OK.equals(statusCode)) {
                    restTemplate.exchange(telegramUrl + url + " 응답실패", HttpMethod.GET, entity, String.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
                restTemplate.exchange(telegramUrl + url + " 타임아웃 실패", HttpMethod.GET, entity, String.class);
                throw e;

            }
        }
    }

    public String testJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String json = "";
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        List<String> list = new ArrayList<String>();
        list.add("127.0.0.1");
        for (String url : list) {
            try {
                ResponseEntity<String> response = restTemplate.exchange("http://" + url + "/apis/health", HttpMethod.GET, entity, String.class);
                HttpStatus statusCode = response.getStatusCode();
                log.debug(url);
                if (!HttpStatus.OK.equals(statusCode)) {
                    restTemplate.exchange(telegramUrl + url + " 서버 응답 실패", HttpMethod.GET, entity, String.class);
                }
                return response.getBody();
            } catch (Exception e) {
                restTemplate.exchange(telegramUrl + url + " 서버 타임 아웃 실패", HttpMethod.GET, entity, String.class);
                throw e;
            }
        }
        return json;
    }

    public void test2() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String kafkaGroupSummeryUrl = "/api/status/kafka/audience-group/KF/groupSummary";

        var ptr = new ParameterizedTypeReference<ClickViewDataSummary>() {};

        var response = restTemplate.exchange(kafkaGroupSummeryUrl, HttpMethod.GET, entity, ptr);

       // ResponseEntity<String> response = restTemplate.exchange(kafkaGroupSummeryUrl, HttpMethod.GET, entity, String.class);

        if(response.getStatusCode().is2xxSuccessful()){
            log.debug(response.getBody().getClickViewData().getTotalLag().toString());
        }


    }

}