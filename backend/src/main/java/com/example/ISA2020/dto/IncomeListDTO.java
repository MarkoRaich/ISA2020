package com.example.ISA2020.dto;
import java.util.*;

public class IncomeListDTO {
	
	private ArrayList<String> labels = new ArrayList<String>();
	
	private ArrayList<Double> values =new ArrayList<Double>();
	
	
	
	
	public IncomeListDTO() {
		super();
	}

	public IncomeListDTO(ArrayList<String> labels, ArrayList<Double> values) {
		super();
		this.labels = labels;
		this.values = values;
	}

	

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public ArrayList<Double> getValues() {
		return values;
	}

	public void setValues(ArrayList<Double> values) {
		this.values = values;
	}
	

	

}
