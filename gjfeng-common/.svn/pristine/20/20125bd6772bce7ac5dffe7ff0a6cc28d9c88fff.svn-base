package cc.messcat.gjfeng.common.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 消息队列生产者
 * @author Karhs
 * @date 2016-02-29 18:26
 *
 */
public class QueueMessageProducerImpl implements QueueMessageProducer{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	/* 
	 * 向默认队列发送消息
	 * @see cc.messcat.jfeimao.common.jms.QueueMessageProducer#sendMessage(java.lang.String)
	 */
	@Override
	public void sendMessage(final String message) {
		String destination =  jmsTemplate.getDefaultDestination().toString();
	    System.out.println("向队列" +destination+ "发送了消息------------" + message);
	    jmsTemplate.send(new MessageCreator() {
	      public Message createMessage(Session session) throws JMSException {
	        return session.createTextMessage(message);
	      }
	    });
		
	}

	/* 
	 * 向指定队列发送消息
	 * @see cc.messcat.jfeimao.common.jms.QueueMessageProducer#sendMessage(javax.jms.Destination, java.lang.String)
	 */
	@Override
	public void sendMessage(Destination destination, final String message) {
		System.out.println("向队列" + destination.toString() + "发送了消息------------" + message);
	    jmsTemplate.send(destination, new MessageCreator() {
	      public Message createMessage(Session session) throws JMSException {
	        return session.createTextMessage(message);
	      }
	    });
		
	}

}
