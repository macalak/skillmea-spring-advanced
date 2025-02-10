package ite.librarymaster;

import ite.librarymaster.util.monitor.JamonMonitorFactory;
import ite.librarymaster.util.monitor.MonitorFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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

	@Bean
	public MonitorFactory monitorFactory(){
		return new JamonMonitorFactory();
	}

}
