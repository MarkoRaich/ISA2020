package com.example.ISA2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.Message;
import com.example.ISA2020.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(/*
							 * @RequestParam("msgText") String messageText,@RequestParam("msgSubject")
							 * String messageSubject
							 */ @RequestBody Message message) {
	
	Message msg = new Message();
	msg.setMessageSubject(message.getMessageSubject());
	msg.setMessageText(message.getMessageText());
	
	rabbitMQSender.send(msg);

	return "Message sent to the RabbitMQ Successfully";
	}
}
