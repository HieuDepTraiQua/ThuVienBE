package com.quanly.thuvien.repository;

import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.model.ReviewModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewModel, String> {

    @Query(value = "{'bookName': ?0}")
    List<ReviewModel> findByNameBook(String bookName);
}
