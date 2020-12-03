package com.example.ISA2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.Message;
import com.example.ISA2020.service.RabbitMQSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(/*
							 * @RequestParam("msgText") String messageText,@RequestParam("msgSubject")
							 * String messageSubject
							 */ @RequestBody Message message) throws JsonProcessingException {
	
	Message msg = new Message();
	msg.setSubject(message.getSubject());
	msg.setMessage(message.getMessage());
	
	/* rabbitMQSender.send(msg); */
	
	ObjectMapper mapper = new ObjectMapper();
    String send = mapper.writeValueAsString(msg);
    
	ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {

        channel.exchangeDeclare("exchange", BuiltinExchangeType.FANOUT);


        channel.basicPublish("exchange", "", null, send.getBytes("UTF-8"));
    } catch (Exception ex) {
    	
    }

	return send;
	}
}
