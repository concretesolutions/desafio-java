package com.khalilpan.handlingErrors;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("ErrorStructure")
public class ErrorStructure {

	private String name;
	private String details;
	
	public ErrorStructure() {
	}

	public ErrorStructure(String name, String details) {
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [name=" + name + ", details=" + details + "]";
	}
	
}
