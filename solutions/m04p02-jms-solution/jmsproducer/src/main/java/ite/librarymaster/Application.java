package ite.librarymaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.JmsTransactionManager;

import jakarta.jms.ConnectionFactory;


/**
 * Application entry point.
 * 
 * @author macalak@itexperts.sk
 *
 */

@SpringBootApplication
public class Application {

	@Autowired
	ConnectionFactory jmsConnectionFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public JmsTransactionManager jmsTransactionManager(){
		return new JmsTransactionManager(jmsConnectionFactory);
	}

}
