package com.quanly.thuvien.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
	
	public PagedListHolder<CategoryModel> get(int page, int size, String keyword) {
		boolean b = keyword == null || keyword.equals(null) || keyword.isEmpty();
		PagedListHolder<CategoryModel> pageList = new PagedListHolder<>();
		if (!b) {
			List<CategoryModel> list = categoryRepository.findByKeyword(keyword);
			pageList.setSource(list);
		}
		else {
			List<CategoryModel> list = categoryRepository.findAll();
			pageList.setSource(list);
		}
		pageList.setPage(page);
		pageList.setPageSize(size);
		return pageList;
	};
}
