package com.ddes.smart_meter_system_back_end.notification;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootTest
public class ThreadPoolTaskSchedulerConfigTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ThreadPoolTaskSchedulerConfig threadPoolTaskSchedulerConfig;

  @Test
  public void testThreadPoolTaskSchedulerBeanExists() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = (ThreadPoolTaskScheduler) applicationContext.getBean("threadPoolTaskScheduler");
    assertThat(threadPoolTaskScheduler).isNotNull();
  }

  @Test
  public void testThreadPoolTaskSchedulerProperties() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = threadPoolTaskSchedulerConfig.threadPoolTaskScheduler();
    assertThat(threadPoolTaskScheduler.getPoolSize()).isEqualTo(1);
    assertThat(threadPoolTaskScheduler.getThreadNamePrefix()).isEqualTo("ThreadPoolTaskScheduler");
  }
}