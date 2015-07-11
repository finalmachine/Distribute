package com.gbi.distribute.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class MessageConsumer extends MessageBase implements Runnable, Consumer {

	public MessageConsumer(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}

	public void run() {
		try {
			// start consuming messages. Auto acknowledge messages.
			channel.basicConsume(queueName, true, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when consumer is registered.
	 */
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + " registered");
	}

	/**
	 * Called when new message is available.
	 */
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
			byte[] body) throws IOException {
		HashMap<String, Integer> map = SerializationUtils.deserialize(body);
		System.out.println("Message Number " + map.get("message number") + " received.");
	}

	public void handleCancel(String consumerTag) {
	}

	public void handleCancelOk(String consumerTag) {
	}

	public void handleRecoverOk(String consumerTag) {
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
	}
}
