package com.quanly.thuvien.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.LibraryCardModel;
@Repository
public interface LibraryCardRepository extends MongoRepository<LibraryCardModel, String>{
	
	@Query(value = "{'idCard': ?0}")
	Optional<LibraryCardModel> findByIdCard(String idCard);
}
