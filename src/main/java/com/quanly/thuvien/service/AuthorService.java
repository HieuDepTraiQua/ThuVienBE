package com.quanly.thuvien.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	public AuthorModel create(AuthorModel author) {
		return authorRepository.save(author);

	};
	
	public List<AuthorModel> getAll() {
		return authorRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<AuthorModel> optional = authorRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			authorRepository.deleteById(id);
		}
	};
	
	public AuthorModel update(AuthorModel author, String id) {
		Optional<AuthorModel> optional = authorRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			author.setId(id);
			if (author.getName() == null || author.getName().isEmpty()) {
				author.setName(optional.get().getName());
			}
			if (author.getNote() == null || author.getNote().isEmpty()) {
				author.setNote(optional.get().getNote());
			}
			return authorRepository.save(author);
		}
	};
	
	public Page<AuthorModel> get(Pageable pageable) {
		return authorRepository.findAll(pageable);
	};

	
}
