package com.quanly.thuvien.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.AuthorModel;
@Repository
public interface AuthorRepository extends MongoRepository<AuthorModel, String>{

}
