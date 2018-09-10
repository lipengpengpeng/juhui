package cc.messcat.gjfeng.common.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 主题发布者实现类
 * @author Karhs
 * @date 2016-02-29 18:53
 *
 */
public class TopicMessageProviderImpl implements TopicMessageProvider{
	
	@Autowired
	private JmsTemplate topicJmsTemplate;

	/* 
	 * 主题发布
	 * @see cc.messcat.jfeimao.common.jms.TopicMessageProvider#publish(javax.jms.Destination, java.lang.String)
	 */
	@Override
	public void publish(final Destination topic, final String message) {
		topicJmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                System.out.println("topic name 是" + topic.toString()
                        + "，发布消息内容为:\t" + message);
                return session.createTextMessage(message);
            }
        });
	}

}
