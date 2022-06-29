package com.quanly.thuvien.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.CustomerModel;
@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel, String>{
	
	@Query("{'$and':[{'name': {$regex: ?0, $options: 'i' }}, {'address': {$regex: ?0,$options: 'i' }]}")
	Page<CustomerModel> findByKeyword(String title, Pageable pageable);
}
