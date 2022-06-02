package com.quanly.thuvien.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryModel create(CategoryModel category) {
		Optional<CategoryModel> optional = categoryRepository.findByTitle(category.getTitle());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Category already exist!");
		} else {
			return categoryRepository.save(category);
		}
	};
	
//	public Page<CategoryModel> get(Pageable pageable, String categoryId) {
//		List<CategoryModel> list = categoryRepository.findByCSYTId(categoryId));
//		return new PageImpl(content)<>(list, pageable, list.size());
//	};

	public List<CategoryModel> getAll() {
		return categoryRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<CategoryModel> optional = categoryRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			categoryRepository.deleteById(id);
		}
	};
	
	public CategoryModel update(CategoryModel category, String id) {
		Optional<CategoryModel> optional = categoryRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			category.setId(id);
			if (category.getTitle() == null || category.getTitle().isEmpty()) {
				category.setTitle(optional.get().getTitle());
			}
			return categoryRepository.save(category);
		}
	};
	
	public Page<CategoryModel> get(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	};
}
