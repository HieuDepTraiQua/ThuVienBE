package com.quanly.thuvien.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "book")
public class BookModel {
 @Id
 private String id;
 
 @Field(value = "nameBook")
 private String nameBook;
 
 @Field(value = "authorId")
 private String authorId;
 
 @Field(value = "categoryId")
 private String categoryId;
 
 @Field (value = "publishYear")
 private String publishYear;

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

public String getAuthorId() {
	return authorId;
}

public void setAuthorId(String authorId) {
	this.authorId = authorId;
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
 
}
