package ite.librarymaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;

import jakarta.jms.ConnectionFactory;
import org.springframework.util.ErrorHandler;


/**
 * Application entry point.
 * 
 * @author macalak@itexperts.sk
 *
 */

@SpringBootApplication
@EnableJms
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

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setErrorHandler(errorHandler);
		return factory;
	}

	@Bean
	public ErrorHandler errorHandler() {
		return t -> {
			// Handle the error here
			System.err.println("Error in JMS Listener: " + t.getMessage());
			// TODO: dead-letter queue
		};
	}
}
