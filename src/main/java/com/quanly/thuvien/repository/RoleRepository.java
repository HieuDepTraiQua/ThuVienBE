package com.quanly.thuvien.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quanly.thuvien.model.RoleModel;

@Repository
public interface RoleRepository extends MongoRepository<RoleModel, String> {

	@Override
	Page<RoleModel> findAll(Pageable pageable);

	@Override
	<S extends RoleModel> S save(S entity);

	@Override
	void deleteById(String id);

	@Override
	List<RoleModel> findAll();

	@Override
	Optional<RoleModel> findById(String id);

	@Query(value = "{'title': ?0 }")
	Optional<RoleModel> findByTitle(String title);

	@Query(value = "{'title': {$regex:?0, $options: 'i' }}")
	Optional<RoleModel> findByTitleRole(String title);

	@Query(value = "{'title': {$regex:?0, $options: 'i' }}", sort = "{'_id':-1}")
	List<RoleModel> findByTitleCode(String title);

	@Query(value = "{'id': ?0}")
	List<RoleModel> findByIdOne(String id);
}
