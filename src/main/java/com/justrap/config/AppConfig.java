package com.justrap.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

 
@Configuration
public class AppConfig {
	
	@Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
 
    @Bean
    public List<String> audioFileExtensions() {
    	List<String> extensions = new ArrayList<>();
    	extensions.add("mp3"); // OGG, FLAC
    	return extensions; 
    }
    
    @Bean
    public String musicsPath() {
    	return "C:\\Users\\Tanguy\\Documents\\JustRapFiles";
    }

}
