package com.github.sejoung.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sejoung.api.service.HealthCheckService;

import io.swagger.annotations.ApiOperation;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;
    
    @ApiOperation(value = "서버 상태확인", notes = "서버 상태확인을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendBatch", method = RequestMethod.GET)
    public String recommendBatch() {
        return healthCheckService.testJson();
    }
    
    @Scheduled(fixedDelay = 60000)
    public void recommendBatchScheduled() {
        healthCheckService.test();
    }
}
