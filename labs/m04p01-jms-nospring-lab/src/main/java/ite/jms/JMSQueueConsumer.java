package ite.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JMS Consumer, which receives message from defined Queue.
 * 
 * @author macalak@itexperts.sk
 *
 */

public class JMSQueueConsumer {
	final static Logger logger = LoggerFactory.getLogger(JMSQueueConsumer.class);

	public static void main(String[] args) throws NamingException {
		// Create the initial context for remote JMS server
		InitialContext initialContext = new InitialContext();
		System.out.println("Context Created");
		  
		Queue queue = (Queue)initialContext.lookup("queue/exampleQueue");
        ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("ConnectionFactory");
        
        try(
           JMSContext jmsContext = cf.createContext("user","password");
        ){
        	// TODO: Create message Consumer and receive message
           JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
        }
        catch(JMSRuntimeException e ){
           System.out.println("expected exception from jmsConsumer: " + e.getMessage());
        }
	}

}
