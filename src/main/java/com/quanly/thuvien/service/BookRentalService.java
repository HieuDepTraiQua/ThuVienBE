package com.quanly.thuvien.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.dto.BookRentalDTO;
import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.BookRentalModel;
import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.model.LibraryCardModel;
import com.quanly.thuvien.model.StaffModel;
import com.quanly.thuvien.repository.BookRentalRepository;
import com.quanly.thuvien.repository.BookRepository;
import com.quanly.thuvien.repository.LibraryCardRepository;
import com.quanly.thuvien.repository.StaffRepository;

@Service
public class BookRentalService {
	@Autowired
	private BookRentalRepository rentalRepository;
	
	@Autowired
	private LibraryCardRepository libraryCardRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public BookRentalModel create(BookRentalModel rental) {
		Optional<LibraryCardModel> opLibrary = libraryCardRepository.findById(rental.getLibraryCardId());
		if (!opLibrary.isPresent()) {
			throw new EntityNotFoundException("Library Card Id not found!");
		}
		Optional<BookModel> opBook = bookRepository.findById(rental.getBookId());
		if (!opBook.isPresent()) {
			throw new EntityNotFoundException("Book Id not found!");
		}
		Optional<StaffModel> opStaff = staffRepository.findById(rental.getStaffId());
		if (!opStaff.isPresent()) {
			throw new EntityNotFoundException("Staff Id not found!");
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
			if (rental.getBookId() == null || rental.getBookId().isEmpty()) {
				rental.setBookId(optional.get().getBookId());
			}
			if (rental.getBorrowedDate() == null || rental.getBorrowedDate().isEmpty()) {
				rental.setBorrowedDate(optional.get().getBorrowedDate());
			}
			if (rental.getLibraryCardId() == null || rental.getLibraryCardId().isEmpty()) {
				rental.setLibraryCardId(optional.get().getLibraryCardId());
			}
			if (rental.getReturnDate() == null || rental.getReturnDate().isEmpty()) {
				rental.setReturnDate(optional.get().getReturnDate());
			}
			if (rental.getStaffId() == null || rental.getStaffId().isEmpty()) {
				rental.setStaffId(optional.get().getStaffId());
			}
			return rentalRepository.save(rental);
		}
	};
	
	public Page<BookRentalDTO> get(Pageable pageable) {
		List<BookRentalModel> listBook = rentalRepository.findAll();
		return new PageImpl<>(getListBookRentalDto(listBook), pageable, listBook.size());
	};
	
	public List<BookRentalDTO> getListBookRentalDto(List<BookRentalModel> listBook) {
		List<BookRentalDTO> listDto = new ArrayList<>();
		for (BookRentalModel book : listBook) {
			BookRentalDTO dto = new BookRentalDTO();
			Optional<LibraryCardModel> opCard = libraryCardRepository.findById(book.getLibraryCardId());
			if (!opCard.isPresent()) {
				throw new EntityNotFoundException("Id Card not found!");
			}
			Optional<StaffModel> opStaff = staffRepository.findById(book.getStaffId());
			if (!opStaff.isPresent()) {
				throw new EntityNotFoundException("Staff Id not found!");
			}
			Optional<BookModel> opBook = bookRepository.findById(book.getBookId());
			if (!opBook.isPresent()) {
				throw new EntityNotFoundException("Book Id not found!");
			}
			dto.setLibraryCard(opCard.get().getIdCard());
			dto.setStaffName(opStaff.get().getName());
			dto.setBookName(opBook.get().getNameBook());
			BeanUtils.copyProperties(book, dto);
			listDto.add(dto);
		}
		return listDto;
	};
	
	public BookRentalDTO getBookRentalDto(BookRentalModel book) {
		BookRentalDTO dto = new BookRentalDTO();
		Optional<LibraryCardModel> opCard = libraryCardRepository.findById(book.getLibraryCardId());
		if (!opCard.isPresent()) {
			throw new EntityNotFoundException("Id Card not found!");
		}
		Optional<StaffModel> opStaff = staffRepository.findById(book.getStaffId());
		if (!opStaff.isPresent()) {
			throw new EntityNotFoundException("Staff Id not found!");
		}
		Optional<BookModel> opBook = bookRepository.findById(book.getBookId());
		if (!opBook.isPresent()) {
			throw new EntityNotFoundException("Book Id not found!");
		}
		dto.setLibraryCard(opCard.get().getIdCard());
		dto.setStaffName(opStaff.get().getName());
		dto.setBookName(opBook.get().getNameBook());
		BeanUtils.copyProperties(book, dto);
		return dto;
	};
}
