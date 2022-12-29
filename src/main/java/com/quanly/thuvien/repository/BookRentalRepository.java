package com.quanly.thuvien.repository;

import com.quanly.thuvien.model.ReviewModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.BookRentalModel;

import java.util.List;

@Repository
public interface BookRentalRepository extends MongoRepository<BookRentalModel, String> {
    @Query(value = "{'userId': ?0}")
    List<BookRentalModel> findByUserId(String userId);
}
