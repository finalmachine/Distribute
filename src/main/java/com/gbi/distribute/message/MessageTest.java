package com.gbi.distribute.message;

public class MessageTest {
	public MessageTest() throws Exception {
		MessageProducer producer = new MessageProducer("MessageTest");
		for (int i = 0; i < 1000; i++) {
			producer.sendMessage("(" + i + ")");
		}
		System.out.println("go");
		MessageConsumer consumer = new MessageConsumer("MessageTest");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();

		producer.close();
	//	consumer.close();
	}

	public static void main(String[] args) throws Exception {
		new MessageTest();
	}
}
