package com.quanly.thuvien.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.BookModel;
@Repository
public interface BookRepository extends MongoRepository<BookModel, String>{

}
