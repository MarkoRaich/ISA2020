package com.example.ISA2020.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.Message;
import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.ISA2020.entity.TenderMessage;
import com.example.ISA2020.entity.TenderOffer;
import com.example.ISA2020.service.PharmacyDrugDetailsService;
import com.example.ISA2020.service.RabbitMQSender;
import com.example.ISA2020.service.RabbitSingleton;
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
	
	@Autowired
	private PharmacyDrugDetailsService pharmacyDrugDetailsService;

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
	
	@GetMapping(value = "/producerTender")
		public String producerTender(@RequestBody TenderMessage tenderMessage) throws IOException {
		
		TenderMessage msg = new TenderMessage();
		msg.setIdentification(tenderMessage.getIdentification());
		msg.setReplyRoutingKey(tenderMessage.getReplyRoutingKey());
		msg.setSendRoutingKey(tenderMessage.getSendRoutingKey());
		
		
		List<PharmacyDrugDetails> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		if(pharmacyDrugDetails.isEmpty()) {
			return null;
		}
		
		int max = 2000;
		int min = 100;
		double leftLimit = 100D;
	    double rightLimit = 2000D;
	
		
		List<TenderOffer> tenderOffers = new ArrayList<>();
		
		for(PharmacyDrugDetails p : pharmacyDrugDetails) {
			//if(p.getDrug().getCode().equals(code)) {
				//System.out.println("Usao je u if");
				TenderOffer t = new TenderOffer();
				//t.setCode(code);
				t.setCode(p.getDrug().getCode());
				t.setName(p.getDrug().getName());
				t.setQuantity(p.getQuantity());
				
				double randomPriceDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
			    BigDecimal bd = new BigDecimal(randomPriceDouble).setScale(2, RoundingMode.HALF_UP);
		        double randomPrice = bd.doubleValue();
				t.setPrice(randomPrice);
				//t.setPharmacyName(p.getPharmacy().getName());
				tenderOffers.add(t);
			//}
		}
		
		
		msg.setOffers(tenderOffers);
		
		ObjectMapper mapper = new ObjectMapper();
	    String send = mapper.writeValueAsString(msg);
	    
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = factory.newConnection();
	         Channel channel = connection.createChannel()) {
	
	        //channel.exchangeDeclare("tender-bolnica-1", BuiltinExchangeType.FANOUT);
	
	
	        channel.basicPublish("tender-bolnica-1", tenderMessage.getSendRoutingKey(), null, send.getBytes("UTF-8"));
	    } catch (Exception ex) {
	    	
	    }
	    RabbitSingleton.getInstance().AddReplyQueue(tenderMessage.getReplyRoutingKey(), "tender-bolnica-1");
		return send;	
	}
	
}
