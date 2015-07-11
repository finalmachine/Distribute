package com.gbi.distribute.message;

import java.util.HashMap;

public class MessageTest {
	public MessageTest() throws Exception {
		MessageConsumer consumer = new MessageConsumer("MessageTest");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();

		MessageProducer producer = new MessageProducer("MessageTest");

		for (int i = 0; i < 100000; i++) {
			HashMap<String, Integer> message = new HashMap<>();
			message.put("message number", i);
			producer.sendMessage(message);
			System.out.println("Message Number " + i + " sent.");
		}
	}

	public static void main(String[] args) throws Exception {
		new MessageTest();
	}
}
