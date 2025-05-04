package ite.jms;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JMS Producer which sends message to defined Queue
 * and asynchronously receives confirmation from broker.
 * 
 * @author macalak@itexperts.sk
 *
 */
public class JMSQueueAsynchronousProducer {
	final static Logger logger = LoggerFactory.getLogger(JMSQueueAsynchronousProducer.class);

	public static void main(String[] args) throws NamingException {
		final CountDownLatch latch = new CountDownLatch(1);
		// Create the initial context for remote JMS server
		InitialContext initialContext = new InitialContext();
		System.out.println("Context Created");
		  
		Queue queue = (Queue)initialContext.lookup("queue/exampleQueue");
        ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("ConnectionFactory");
        
        try(
           JMSContext jmsContext = cf.createContext("user","user01*");
        ){
           JMSProducer jmsProducer = jmsContext.createProducer();
           jmsProducer.setAsync(new DefaultCompletionListener(latch));
           jmsProducer.send(queue, "Hallo from JMS Producer!");
           latch.await(5, TimeUnit.SECONDS);
        }
        catch(JMSRuntimeException | InterruptedException e){
			logger.error(e.getMessage());
        }
	}

}
