package com.swastik.app.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MyJMSFactory {

	public void postAMessage() {
		try {
			Context ctx = new InitialContext();
			QueueConnectionFactory qConnFactory = (QueueConnectionFactory) ctx.lookup("jms/EPS_QM");
			QueueConnection queueConnection = qConnFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, -1);
			Queue queue = (Queue) ctx.lookup("jms/EPS_Q");
			System.out.println(queue.getQueueName());
		} catch (JMSException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
