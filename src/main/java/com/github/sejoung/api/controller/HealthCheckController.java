package com.github.sejoung.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.github.sejoung.api.service.HealthCheckService;


@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendBatch", method = RequestMethod.GET)
    public String recommendBatch() {
        return healthCheckService.testJson();
    }

    //@Scheduled(fixedDelay = 60000)
    public void recommendBatchScheduled() {
        healthCheckService.test();
    }


    @ResponseBody
    @GetMapping("/test")
    public void test() {
        healthCheckService.test2();
    }




}
