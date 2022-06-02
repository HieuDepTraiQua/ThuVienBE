package com.quanly.thuvien.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "customer")
public class CustomerModel {
	@Id
	private String id;
	
	@Field(value = "name")
	private String name;
	
	@Field (value = "address")
	private String address;
	
	@Field (value = "libraryCardId")
	private String libraryCardId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLibraryCardId() {
		return libraryCardId;
	}

	public void setLibraryCardId(String libraryCardId) {
		this.libraryCardId = libraryCardId;
	}

}
