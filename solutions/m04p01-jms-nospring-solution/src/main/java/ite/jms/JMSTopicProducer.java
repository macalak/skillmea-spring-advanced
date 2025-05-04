package ite.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Topic;
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
public class JMSTopicProducer {
	final static Logger logger = LoggerFactory.getLogger(JMSTopicProducer.class);

	public static void main(String[] args) throws NamingException {
		// Create the initial context for remote JMS server
		InitialContext initialContext = new InitialContext();
		System.out.println("Context Created");
		  
		Topic topic = (Topic)initialContext.lookup("exampleTopic");
        ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("ConnectionFactory");
        
        try(
           JMSContext jmsContext = cf.createContext("expert","secret");
        ){
           JMSProducer jmsProducer = jmsContext.createProducer();
           jmsProducer.send(topic, "Hallo from JMS Producer!");
        }
        catch(JMSRuntimeException e){
			logger.error(e.getMessage());
        }
	}

}
