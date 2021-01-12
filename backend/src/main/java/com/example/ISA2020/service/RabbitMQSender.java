package com.example.ISA2020.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Message;
import com.example.ISA2020.entity.TenderMessage;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Message message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
		System.out.println("Send msg = " + message);
	    
	}
	
	public void sendTender(TenderMessage tenderMessage) {
		rabbitTemplate.convertAndSend(exchange, routingkey, tenderMessage);
		System.out.println("Send msg = " + tenderMessage);	    
	}
}
