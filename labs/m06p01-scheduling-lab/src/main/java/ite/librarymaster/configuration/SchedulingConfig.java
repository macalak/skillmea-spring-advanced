package ite.librarymaster.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulingConfig {

   // TODO: Configure task scheduler bean
   @Bean
   public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
       ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
       threadPoolTaskScheduler.setPoolSize(10);
       threadPoolTaskScheduler.setThreadNamePrefix("my-scheduler");
       return threadPoolTaskScheduler;
   }
}
