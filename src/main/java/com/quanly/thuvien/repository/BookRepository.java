package com.quanly.thuvien.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.BookModel;
@Repository
public interface BookRepository extends MongoRepository<BookModel, String>{
	
	@Query(value = "{'nameBook': ?0 }")
	Optional<BookModel> findByName(String nameBook);

}
