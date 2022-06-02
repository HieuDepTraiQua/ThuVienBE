package com.quanly.thuvien.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.CategoryModel;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel, String>{

	@Query(value = "{'title': ?0}")
	Optional<CategoryModel> findByTitle(String title);
}
