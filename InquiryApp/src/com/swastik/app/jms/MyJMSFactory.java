package com.swastik.app.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

public class MyJMSFactory {
	
	@Resource(name="jms/EPS_QM")
	private QueueConnectionFactory qFactory;
	
	@Resource(name="jms/EPS_Q")
	private Queue q;
	
	public void postAMessage() {
		try {
			System.out.println(q.getQueueName());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
