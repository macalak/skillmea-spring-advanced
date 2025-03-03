package ite.librarymaster;

import ite.librarymaster.util.monitor.JamonMonitorFactory;
import ite.librarymaster.util.monitor.MonitorFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * Application entry point.
 * 
 * @author macalak@itexperts.sk
 *
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public ApplicationEventMulticaster applicationEventMulticaster(){
//		SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
//		multicaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//		return multicaster;
//	}

	@Bean
	public MonitorFactory monitorFactory(){
		return new JamonMonitorFactory();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
		return modelMapper;
	}

}
