package cc.messcat.gjfeng.common.jms;

import javax.jms.Destination;

/**
 * 消息队列生产者接口
 * @author Karhs
 * @date 2016-02-29 18:39
 *
 */
public interface QueueMessageProducer{

	/**
	 * 向默认队列发送消息
	 * @author Karhs
	 * @date 2016-02-29 18:39
	 * @param message
	 */
	public void sendMessage(final String message);
	
	/**
	 * 向指定队列发送消息
	 * @author Karhs
	 * @date 2016-02-29 18:39
	 * @param destination
	 * @param message
	 */
	public void sendMessage(Destination destination,final String message);
}
