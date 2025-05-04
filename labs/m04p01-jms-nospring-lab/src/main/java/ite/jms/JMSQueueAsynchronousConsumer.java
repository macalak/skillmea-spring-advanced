package ite.jms;

import java.util.concurrent.CountDownLatch;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JMS Consumer, which receives messages asynchronously
 * using Message listener.
 * 
 * @author macalak@itexperts.sk
 *
 */

public class JMSQueueAsynchronousConsumer {
	final static int MESSAGES_TO_CONSUME = 2;
	final static Logger logger = LoggerFactory.getLogger(JMSQueueAsynchronousConsumer.class);

	public static void main(String[] args) throws NamingException {
		final CountDownLatch latch = new CountDownLatch(MESSAGES_TO_CONSUME);
		
		// Create the initial context for remote JMS server
		InitialContext initialContext = new InitialContext();
		System.out.println("Context Created");
		  
		Queue queue = (Queue)initialContext.lookup("queue/exampleQueue");
        ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("ConnectionFactory");
        
        try(
           JMSContext jmsContext = cf.createContext("user","password");
        ){
           JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
           
           logger.info("Registerring MessageListener ...");
           // TODO:  Set DefaultMessageListener to Consumer and start receiving messages
           logger.info("Waiting to consume {} messages ...", MESSAGES_TO_CONSUME);
           latch.await();
           jmsContext.stop();
        }
        catch(JMSRuntimeException | InterruptedException e){
           System.out.println("expected exception from jmsConsumer: " + e.getMessage());
        }
	}

}
