package com.quanly.thuvien.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.quanly.thuvien.model.*;
import com.quanly.thuvien.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.dto.BookRentalDTO;

@Service
public class BookRentalService {
	@Autowired
	private BookRentalRepository rentalRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public BookRentalModel create(BookRentalModel rental) throws Exception {
		Optional<AccountModel> opAccount = accountRepository.findById(rental.getUserId());
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("UserId Id not found!");
		}
		Optional<BookModel> book = bookRepository.findByName(rental.getNameBook());
		if (book.isPresent()) {
			if (Integer.parseInt(book.get().getRemainingStock()) > Integer.parseInt(rental.getQuantity())){
				int remaining = Integer.parseInt(book.get().getRemainingStock()) - Integer.parseInt(rental.getQuantity());
				book.get().setRemainingStock(Integer.toString(remaining));
				bookRepository.save(book.get());
			} else {
				throw new Exception("No more in stock. Please try again");
			}

		}
		return rentalRepository.save(rental);
	};
	
	public List<BookRentalModel> getAll() {
		return rentalRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<BookRentalModel> optional = rentalRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			rentalRepository.deleteById(id);
		}
	};
	
	public BookRentalModel update(BookRentalModel rental, String id) {
		Optional<BookRentalModel> optional = rentalRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			rental.setId(id);
			if (rental.getAuthor() == null || rental.getAuthor().isEmpty()) {
				rental.setAuthor(optional.get().getAuthor());
			}
			if (rental.getImage() == null || rental.getImage().isEmpty()) {
				rental.setImage(optional.get().getImage());
			}
			if (rental.getNameBook() == null || rental.getNameBook().isEmpty()) {
				rental.setNameBook(optional.get().getNameBook());
			}
			if (rental.getPrice() == null || rental.getPrice().isEmpty()) {
				rental.setPrice(optional.get().getPrice());
			}
			if (rental.getQuantity() == null || rental.getQuantity().isEmpty()) {
				rental.setQuantity(optional.get().getQuantity());
			}
			if (rental.getUserId() == null || rental.getUserId().isEmpty()) {
				rental.setUserId(optional.get().getUserId());
			}
			return rentalRepository.save(rental);
		}
	};
	
	public Page<BookRentalDTO> get(Pageable pageable, String userId) {
		List<BookRentalModel> listBook = rentalRepository.findByUserId(userId);
		return new PageImpl<>(getListBookRentalDto(listBook), pageable, listBook.size());
	};

	public String getTotalPrice(String userId){
		List<BookRentalModel> listBook = rentalRepository.findByUserId(userId);
		int sum = 0;
		for (BookRentalModel book : listBook){
			sum+= Integer.parseInt(book.getPrice())*Integer.parseInt(book.getQuantity());
		}
		return String.valueOf(sum);
	}

	public List<BookRentalDTO> getListBookRentalDto(List<BookRentalModel> listBook) {
		List<BookRentalDTO> listDto = new ArrayList<>();
		for (BookRentalModel book : listBook) {
			BookRentalDTO dto = getBookRentalDto(book);
			listDto.add(dto);
		}
		return listDto;
	};

	public BookRentalDTO getBookRentalDto(BookRentalModel book) {
		BookRentalDTO dto = new BookRentalDTO();
		Optional<AccountModel> opAccount = accountRepository.findById(book.getUserId());
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account Id not found!");
		}
		dto.setFullname(opAccount.get().getFullname());
		BeanUtils.copyProperties(book, dto);
		return dto;
	};
}
