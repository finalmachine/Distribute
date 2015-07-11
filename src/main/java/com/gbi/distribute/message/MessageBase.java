package com.gbi.distribute.message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageBase {
	protected Channel channel;
    protected Connection connection;
    protected String queueName;
     
    public MessageBase(String queueName) throws IOException, TimeoutException{
         this.queueName = queueName;
         
         //Create a connection factory
         ConnectionFactory factory = new ConnectionFactory();
         //hostname of your rabbitmq server
         factory.setHost("localhost");
         //getting a connection
         connection = factory.newConnection();
         //creating a channel
         channel = connection.createChannel();
         //declaring a queue for this channel. If queue does not exist,
         //it will be created on the server.
         channel.queueDeclare(queueName, false, false, false, null);
    }
     
     
    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     * @throws TimeoutException 
     */
     public void close() throws IOException, TimeoutException{
         this.channel.close();
         this.connection.close();
     }
}
