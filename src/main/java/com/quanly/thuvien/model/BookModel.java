package com.quanly.thuvien.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "book")
public class BookModel {
	@Id
	private String id;

	@Field(value = "nameBook")
	private String nameBook;

	@Field(value = "author")
	private String author;

	@Field(value = "categoryId")
	private String categoryId;

	@Field(value = "publishYear")
	private String publishYear;

	@Field(value = "pageOfBook")
	private String pageOfBook;

	@Field(value = "description")
	private String description;

	@Field(value = "image")
	private String image;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
