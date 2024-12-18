package com.ddes.smart_meter_system_back_end.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan
public class ThreadPoolTaskSchedulerConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
          = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setThreadNamePrefix(
          "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
    
}
