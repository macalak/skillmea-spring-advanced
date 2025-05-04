package ite.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JMS Producer which sends message to defined Queue.
 * 
 * @author macalak@itexperts.sk
 *
 */
public class JMSQueueProducer {
	final static Logger logger = LoggerFactory.getLogger(JMSQueueProducer.class);

	public static void main(String[] args) throws NamingException {
		// Create the initial context for remote JMS server
		InitialContext initialContext = new InitialContext();
		System.out.println("Context Created");
		  
		// TODO: Get references to exampleQueue and  ConnectionFactory using JNDI lookup
		Queue queue = null;
        ConnectionFactory cf = null;

        try(
			// TODO: Create JMS context
			JMSContext jmsContext = null;
        ){
        // TODO: Create producer and send a text message to exampleQueue JMS Queue
        }
        catch(JMSRuntimeException e){
			logger.error(e.getMessage());
        }
	}

}
