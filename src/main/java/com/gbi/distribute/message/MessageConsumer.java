package com.gbi.distribute.message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.ShutdownSignalException;

public class MessageConsumer extends MessageBase implements Runnable, Consumer {
	
	protected String consumerTag = null;

	public MessageConsumer(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}

	public void run() {
		try {
			// start consuming messages. Auto acknowledge messages.
			channel.basicConsume(queueName, false, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("run end");
	}

	/**
	 * Called when consumer is registered.
	 */
	public void handleConsumeOk(String consumerTag) {
		this.consumerTag = consumerTag;
	}

	/**
	 * Called when new message is available.
	 */
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
			byte[] body) throws IOException {
		GetResponse response = channel.basicGet(queueName, false);
		if (response != null) {
			System.out.println(SerializationUtils.deserialize(response.getBody()));
		}
		
		String message = SerializationUtils.deserialize(body);
		System.out.println(consumerTag + ">" + message + " received.");
		channel.basicAck(envelope.getDeliveryTag(), false);
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		System.out.println("handleCancel");
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		System.out.println("handleCancelOk");
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		System.out.println("handleRecoverOk");
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		System.out.println("shut down");
		System.exit(0);
	}
}
