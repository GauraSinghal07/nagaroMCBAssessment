package com.assignment.mcb.enums;

public enum Status {

	COMPLETED("completed"),
	FAILED("failed");
	
	private String status;

	private Status(String status) {
		this.status = status;
	}
	
}
