package com.quanly.thuvien.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.LibraryCardModel;
import com.quanly.thuvien.repository.LibraryCardRepository;

@Service
public class LibraryCardService {
	@Autowired
	private LibraryCardRepository cardRepository;
	
	public LibraryCardModel create(LibraryCardModel card) {
		Optional<LibraryCardModel> opCard = cardRepository.findByIdCard(card.getIdCard());
		if (opCard.isPresent()) {
			throw new EntityNotFoundException("Id card already exist!");
		}
		return cardRepository.save(card);
	};
	
	public List<LibraryCardModel> getAll() {
		return cardRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<LibraryCardModel> optional = cardRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			cardRepository.deleteById(id);
		}
	};
	
	public LibraryCardModel update(LibraryCardModel card, String id) {
		Optional<LibraryCardModel> optional = cardRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			card.setId(id);
			if (card.getStartDate() == null || card.getStartDate().isEmpty()) {
				card.setStartDate(optional.get().getStartDate());
			}
			if (card.getEndDate() == null || card.getEndDate().isEmpty()) {
				card.setEndDate(optional.get().getEndDate());
			}
			if (card.getIdCard() == null || card.getIdCard().isEmpty()) {
				card.setIdCard(optional.get().getIdCard());
			}
			return cardRepository.save(card);
		}
	};
	
	public Page<LibraryCardModel> get(Pageable pageable) {
		return cardRepository.findAll(pageable);
	};
}
