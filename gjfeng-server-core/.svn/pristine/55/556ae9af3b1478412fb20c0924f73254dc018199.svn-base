package cc.messcat.gjfeng.queue;

import java.util.concurrent.DelayQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.dao.order.GjfOrderInfoDao;

public class OrderDelayQueue {
	
	@Autowired
	@Qualifier("gjfOrderInfoDao")
	private GjfOrderInfoDao gjfOrderInfoDao;

	private DelayQueue<OrderDelayItem> orderTimeoutQueue = new DelayQueue<>();

    private Thread workthread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    OrderDelayItem item = orderTimeoutQueue.take();//从延迟队列中获取到期的元素
                    if (item != null) {
                        //System.out.println(item.getOrderId() + ";" + item.getOpration());
                        if (item.getOpration() == 1) {
                           System.out.println("订单还没付款，赶紧发邮件通知用户付款啊");
                           //gjfOrderInfoDao.updateOrderToOverdue(item.getOrderSn());
                        } else if (item.getOpration() == 2) {
                           System.out.println("订单超过有效时间，该取消了");
                           gjfOrderInfoDao.updateOrderToOverdue(item.getOrderSn());
                        }
                    }
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    });
    
    public void start(){
        workthread.start();
    }

    public void addItem(String orderId,int timeout,int opration){
        orderTimeoutQueue.add(new OrderDelayItem(orderId,timeout,opration));
    }
}
