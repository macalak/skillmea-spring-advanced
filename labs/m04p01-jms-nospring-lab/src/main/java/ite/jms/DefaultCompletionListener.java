package ite.jms;

import java.util.concurrent.CountDownLatch;

import javax.jms.CompletionListener;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JMS Completion listener, which receives result of message
 * processing from broker.
 * 
 * @author macalak@itexperts.sk
 *
 */
public class DefaultCompletionListener implements CompletionListener {
	final Logger logger = LoggerFactory.getLogger(DefaultCompletionListener.class);
	private CountDownLatch latch;
	
	
	public DefaultCompletionListener(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void onCompletion(Message message) {
		try {
			logger.info("Message {} acknowledged by Broker", message.getBody(String.class));
		} catch (JMSException e) {
			logger.error(e.getMessage());
		}finally{
			latch.countDown();
		}
	}

	@Override
	public void onException(Message message, Exception e) {
		latch.countDown();
		logger.error(e.getMessage());
	}

}
