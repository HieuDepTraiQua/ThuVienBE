package com.quanly.thuvien.repository;

import com.quanly.thuvien.model.ReviewModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewModel, String> {
}
