package com.quanly.thuvien.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.model.StaffModel;
@Repository
public interface StaffRepository extends MongoRepository<StaffModel, String> {
	
	@Query(value = "{'phoneNumber': ?0}")
	Optional<StaffModel> findByPhoneNumber(String phoneNumber);
}
