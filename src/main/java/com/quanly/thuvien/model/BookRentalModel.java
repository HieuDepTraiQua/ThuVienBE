package com.quanly.thuvien.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "bookRental")
public class BookRentalModel {
	@Id
	private String id;
	
	@Field(value = "libraryCardId")
	private String libraryCardId;
	
	@Field(value = "staffId")
	private String staffId;
	
	@Field(value = "bookId")
	private String bookId;
	
	@Field(value = "borrowedDate")
	private String borrowedDate;
	
	@Field(value = "returnDate")
	private String returnDate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibraryCardId() {
		return libraryCardId;
	}

	public void setLibraryCardId(String libraryCardId) {
		this.libraryCardId = libraryCardId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(String borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
