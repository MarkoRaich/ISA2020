package com.example.ISA2020.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@subject" , scope = Message.class)*/
public class Message {

	private String subject;
	private String message;

	public Message(String subject, String message) {
		super();
		this.subject = subject;
		this.message = message;
	}

	public Message() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [subject=" + subject + ", message=" + message + "]";
	}

}
