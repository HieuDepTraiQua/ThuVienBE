package com.quanly.thuvien.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.CategoryModel;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel, String>{

	@Query(value = "{'title': ?0}")
	Optional<CategoryModel> findByTitle(String title);
	
//	@Query("{'$or':[{'title': {$regex: ?0, $options: 'i' }}, { 'capacityType' : { $regex: ?0, $options: 'i' } }]}")
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<CategoryModel> findByKeyword(String keyword);
	
	@Query("{'$and':[{'title': {$regex: ?1, $options: 'i' }}]}")
	Page<CategoryModel> findByKeyword(String title, Pageable pageable);
}
