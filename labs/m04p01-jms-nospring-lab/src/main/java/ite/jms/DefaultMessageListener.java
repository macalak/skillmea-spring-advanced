package ite.jms;

import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JMS Message Listener, which should be registered with 
 * JMS context in order to receive messages.
 * 
 * @author macalak@itexperts.sk
 *
 */

public class DefaultMessageListener implements MessageListener {
	final Logger logger = LoggerFactory.getLogger(DefaultMessageListener.class);
	
	private static int messageCounter=1;
	private CountDownLatch latch;
	
	public DefaultMessageListener(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void onMessage(Message message) {
  		latch.countDown();
		messageCounter++;
	}

}
