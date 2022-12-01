package com.quanly.thuvien.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.AccountModel;

@Repository
public interface AccountRepository extends MongoRepository<AccountModel, String>{
	@Query(value = "{'username': ?0}")
	Optional<AccountModel> findByUsername(String username);
	@Query(value = "{'phoneNumber': ?0}")
	Optional<AccountModel> findByPhoneNumber(String phoneNumber);


}
