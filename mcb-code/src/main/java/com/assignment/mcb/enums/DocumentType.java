package com.assignment.mcb.enums;

public enum DocumentType {

	PERSONAL("personal"),
	ADDRESS("address"),
	BUSINESS("business");
	
	private String documentType;

	private DocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	
}
