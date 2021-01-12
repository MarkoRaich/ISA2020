package com.example.ISA2020.service;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitSingleton {
	
	private static RabbitSingleton instance;
	private Channel channel;
	
	public RabbitSingleton () {
	
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try {
	    	Connection connection = factory.newConnection();
	        channel = connection.createChannel();
	        //channel.exchangeDeclare("tender-bolnica-1", BuiltinExchangeType.FANOUT);
	
	    } catch (Exception ex) {
	    	
	    }
	}
	
	static {
		try {
			instance = new RabbitSingleton();
		}catch (Exception ex) {
			
		}
	}
	
	public static RabbitSingleton getInstance() {
		return instance;
	}
	
	public void AddReplyQueue(String replyRoutingKey, String exchange) throws IOException {
		
		UUID id = UUID.randomUUID();
		
		channel.exchangeDeclare("tender-bolnica-1", BuiltinExchangeType.DIRECT);
		
		channel.queueDeclare(id.toString(), true, false, false, null);
		channel.queueBind(id.toString(), exchange, replyRoutingKey);
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(message);
	    };
	    channel.basicConsume(id.toString(), true, deliverCallback, consumerTag -> { });
	}
	
	
}
