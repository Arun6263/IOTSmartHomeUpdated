package com.akshar.iot.smarthome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akshar.iot.smarthome.webservice.rest.UserDeviceRestController;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootIOTSmartHomeApplication extends SpringBootServletInitializer {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SpringBootIOTSmartHomeApplication.class);
	
	@Autowired
	Runnable MessageListener;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootIOTSmartHomeApplication.class);
	}
	public static void main(String[] args){
		
		SpringApplication.run(SpringBootIOTSmartHomeApplication.class);
		
	}

	
	
	
	
	@Bean
	public CommandLineRunner schedulingRunner(TaskExecutor executor) {
	    return new CommandLineRunner() {
	        public void run(String... args) throws Exception {
	            executor.execute(MessageListener);
	        }
	    };
	}
}





