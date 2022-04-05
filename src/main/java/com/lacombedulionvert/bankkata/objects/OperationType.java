package com.lacombedulionvert.bankkata.objects;

public enum OperationType {

	DEPOSIT("DEPOSIT"),
	WITHDRAWAL("WITHDRAWAL");
	
	private String name;

	private OperationType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
