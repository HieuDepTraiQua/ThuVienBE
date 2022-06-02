package com.quanly.thuvien.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.BookRentalModel;
@Repository
public interface BookRentalRepository extends MongoRepository<BookRentalModel, String> {

}
