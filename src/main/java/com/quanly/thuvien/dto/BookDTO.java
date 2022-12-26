package com.quanly.thuvien.dto;

public class BookDTO {
	private String id;
	
	 private String nameBook;
	 
	 private String categoryId;
	 
	 private String categoryTitle;
	 
	 private String author;

	 private String publishYear;

	private String image;

	private String pageOfBook;

	private String description;

	private String price;

	private String remainingStock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPageOfBook() {
		return pageOfBook;
	}

	public void setPageOfBook(String pageOfBook) {
		this.pageOfBook = pageOfBook;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemainingStock() {
		return remainingStock;
	}

	public void setRemainingStock(String remainingStock) {
		this.remainingStock = remainingStock;
	}
}
