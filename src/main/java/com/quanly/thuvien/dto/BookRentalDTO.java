package com.quanly.thuvien.dto;

public class BookRentalDTO {
	private String id;
	private String libraryCardId;
	private String libraryCard;
	private String staffId;
	private String staffName;
	private String bookId;
	private String bookName;
	private String borrowedDate;
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
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

	public String getLibraryCard() {
		return libraryCard;
	}
	public void setLibraryCard(String libraryCard) {
		this.libraryCard = libraryCard;
	}

	
}
