package ite.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;
import javax.jms.Session;
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
		  
		Queue queue = (Queue)initialContext.lookup("queue/exampleQueue");
        ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("ConnectionFactory");
        
        try(
           //JMSContext jmsContext = cf.createContext("expert","secret");
           JMSContext jmsContext = cf.createContext("user","password",Session.SESSION_TRANSACTED);
        ){
        
           JMSProducer jmsProducer = jmsContext.createProducer();
           jmsProducer.send(queue, "Hallo from JMS Producer!");
           
           //jmsContext.rollback();
           jmsContext.commit();
        }
        catch(JMSRuntimeException e){
			logger.error(e.getMessage());
        }
	}

}
