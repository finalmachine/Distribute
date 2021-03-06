package com.gbi.distribute.message;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.MessageProperties;

public class MessageProducer extends MessageBase {

	public MessageProducer(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, SerializationUtils.serialize(object));
    }  
}
