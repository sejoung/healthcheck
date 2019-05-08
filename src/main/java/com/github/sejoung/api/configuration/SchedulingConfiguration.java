package com.github.sejoung.api.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@Configuration
@Slf4j
public class SchedulingConfiguration implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.setErrorHandler(t -> log.error("Exception in @Scheduled task. ", t));
        taskScheduler.setThreadNamePrefix("@scheduled-");
        taskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }
}
