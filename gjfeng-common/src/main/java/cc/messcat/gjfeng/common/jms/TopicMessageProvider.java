package cc.messcat.gjfeng.common.jms;

import javax.jms.Destination;

/**
 * 主题发布者接口
 * @author Karhs
 * @date 2016-02-29 18:52
 *
 */
public interface TopicMessageProvider {

	/**
	 * 
	 * @param topic
	 * @param message
	 */
	public void publish(final Destination topic, final String message);
}
