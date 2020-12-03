package com.example.ISA2020.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@subject", scope = Message.class)
public class Message {
	
	private String messageSubject;
	private String messageText;
	
	
	public Message() {
		super();
	}


	public Message(String messageSubject, String messageText) {
		super();
		this.messageSubject = messageSubject;
		this.messageText = messageText;
	}


	public String getMessageSubject() {
		return messageSubject;
	}


	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}


	public String getMessageText() {
		return messageText;
	}


	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}


	@Override
	public String toString() {
		return "Message [messageSubject=" + messageSubject + ", messageText=" + messageText + "]";
	}


	
	
	
	
}
