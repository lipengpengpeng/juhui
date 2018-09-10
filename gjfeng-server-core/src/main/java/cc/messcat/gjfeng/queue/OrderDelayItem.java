package cc.messcat.gjfeng.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单过期通知队列
 * @author Karhs
 *
 */
public class OrderDelayItem implements Delayed {
    private long time; 			//过期时间(分钟)
    private String orderSn; 	//订单号
    private int opration;  		//到达指定的时间后需要做什么

    public OrderDelayItem(String orderSn, int timeout, int opration) {
        this.orderSn = orderSn;
        this.opration = opration;
        time = TimeUnit.NANOSECONDS.convert(timeout, TimeUnit.MINUTES) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //System.out.println("getdelay()");
        return time - System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.valueOf(this.time).compareTo(Long.valueOf(((OrderDelayItem) o).time));
    }
    
    public static void main(String[] arg){
    	OrderDelayQueue orderDelayQueue=new OrderDelayQueue();
    	orderDelayQueue.addItem("1",1,1);//1分钟后提醒用户付款
        orderDelayQueue.addItem("1",2,2);//2分钟后取消订单
        orderDelayQueue.start();
    }

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public int getOpration() {
		return opration;
	}

	public void setOpration(int opration) {
		this.opration = opration;
	}

}

