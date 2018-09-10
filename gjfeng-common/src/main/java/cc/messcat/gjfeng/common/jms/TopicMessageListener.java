package cc.messcat.gjfeng.common.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 主题监听
 * @author Karhs
 * @date 2016-02-29 18:46
 *
 */
public class TopicMessageListener implements MessageListener{

	
	/* 
	 * 当收到消息时，自动调用该方法
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
        try {
            System.out.println("TopicMessageListener \t" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
	}

}
