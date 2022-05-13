package com.assignment.mcb.enums;

import java.util.Arrays;

public enum DocumentType {

	PERSONAL("personal"), ADDRESS("address"), BUSINESS("business");

	private String documentType;

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	private DocumentType(String documentType) {
		this.documentType = documentType;
	}

	public static final DocumentType getByValue(String value) {
		return Arrays.stream(DocumentType.values()).filter(doc -> doc.documentType.equals(value))
				.findFirst().orElse(null);
	}

}
