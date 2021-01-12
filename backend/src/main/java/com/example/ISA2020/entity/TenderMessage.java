package com.example.ISA2020.entity;

import java.util.ArrayList;
import java.util.List;

public class TenderMessage {
	
	private String identification; //(neki podaci kako stupiti u kontakt)
    private String replyRoutingKey; //(routingkey za rabbitmq da bi bolnica mogla da obavesti)
    private String sendRoutingKey; //na njega se salje sa isa strane
    private List<TenderOffer> offers;
    
    
    public TenderMessage() {
		super();
	}
    
	public TenderMessage(String identification, String replyRoutingKey, String sendRoutingKey,  List<TenderOffer> offers) {
		super();
		this.identification = identification;
		this.replyRoutingKey = replyRoutingKey;
		this.sendRoutingKey = sendRoutingKey;
		this.offers = offers;
	}
	
	public TenderMessage(String identification, String replyRoutingKey, String sendRoutingKey) {
		super();
		this.identification = identification;
		this.replyRoutingKey = replyRoutingKey;
		this.sendRoutingKey = sendRoutingKey;
		this.offers = new ArrayList<>();
	}


	public String getIdentification() {
		return identification;
	}


	public void setIdentification(String identification) {
		this.identification = identification;
	}


	public String getReplyRoutingKey() {
		return replyRoutingKey;
	}


	public void setReplyRoutingKey(String replyRoutingKey) {
		this.replyRoutingKey = replyRoutingKey;
	}


	public List<TenderOffer> getOffers() {
		return offers;
	}


	public void setOffers(List<TenderOffer> offers) {
		this.offers = offers;
	}

	public String getSendRoutingKey() {
		return sendRoutingKey;
	}

	public void setSendRoutingKey(String sendRoutingKey) {
		this.sendRoutingKey = sendRoutingKey;
	}
	
	
	
    
}
