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
import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.repository.AuthorRepository;
import com.quanly.thuvien.repository.BookRepository;
import com.quanly.thuvien.repository.CategoryRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public BookModel create(BookModel book) {
		Optional<AuthorModel> opAuthor = authorRepository.findById(book.getAuthorId());
		if (!opAuthor.isPresent()) {
			throw new EntityNotFoundException("Author Id not found!");
		}
		Optional<CategoryModel> opCate = categoryRepository.findById(book.getCategoryId());
		if (!opCate.isPresent()) {
			throw new EntityNotFoundException("Category Id not found!");
		}
		return bookRepository.save(book);
	};

	public List<BookModel> getAll() {
		return bookRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<BookModel> optional = bookRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			bookRepository.deleteById(id);
		}
	};

	public BookModel update(BookModel book, String id) {
		Optional<BookModel> optional = bookRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			book.setId(id);
			if (book.getNameBook() == null || book.getNameBook().isEmpty()) {
				book.setNameBook(optional.get().getNameBook());
			}
			if (book.getAuthorId() == null || book.getAuthorId().isEmpty()) {
				book.setAuthorId(optional.get().getAuthorId());
			}
			if (book.getCategoryId() == null || book.getCategoryId().isEmpty()) {
				book.setCategoryId(optional.get().getCategoryId());
			}
			if (book.getPublishYear() == null || book.getPublishYear().isEmpty()) {
				book.setPublishYear(optional.get().getPublishYear());
			}
			return bookRepository.save(book);
		}
	};

	public Page<BookDTO> get(Pageable pageable) {
		List<BookModel> listBook = bookRepository.findAll();
		return new PageImpl<>(getListBookDto(listBook), pageable, listBook.size());
	};

	public List<BookDTO> getListBookDto(List<BookModel> listBook) {
		List<BookDTO> listDto = new ArrayList<>();
		for (BookModel book : listBook) {
			BookDTO dto = new BookDTO();
			Optional<AuthorModel> opAuthor = authorRepository.findById(book.getAuthorId());
			if (!opAuthor.isPresent()) {
				throw new EntityNotFoundException("Author Id not found!");
			}
			Optional<CategoryModel> opCate = categoryRepository.findById(book.getCategoryId());
			if (!opCate.isPresent()) {
				throw new EntityNotFoundException("Category Id not found!");
			}
			dto.setAuthorName(opAuthor.get().getName());
			dto.setCategoryTitle(opCate.get().getTitle());
			BeanUtils.copyProperties(book, dto);
			listDto.add(dto);
		}
		return listDto;
	};

	public BookDTO getBookDto(BookModel book) {
		BookDTO dto = new BookDTO();
		Optional<AuthorModel> opAuthor = authorRepository.findById(book.getAuthorId());
		if (!opAuthor.isPresent()) {
			throw new EntityNotFoundException("Author Id not found!");
		}
		Optional<CategoryModel> opCate = categoryRepository.findById(book.getCategoryId());
		if (!opCate.isPresent()) {
			throw new EntityNotFoundException("Category Id not found!");
		}
		dto.setAuthorName(opAuthor.get().getName());
		dto.setCategoryTitle(opCate.get().getTitle());
		BeanUtils.copyProperties(book, dto);
		return dto;
	};

//	public String uploadPhoto(MultipartFile file, String title) throws Exception {
//
//		if (!file.getContentType().startsWith("image")) {
//			throw new EntityNotFoundException("Upload file is not image!");
//		}
//		String fileName = "";
//		Date date = new Date();
//		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		String month = localDate.getMonthValue() >= 10 ? String.valueOf(localDate.getMonthValue())
//				: ("0" + localDate.getMonthValue());
//		String year = String.valueOf(localDate.getYear());
//		String day = localDate.getDayOfMonth() >= 10 ? String.valueOf(localDate.getDayOfMonth())
//				: ("0" + (localDate.getDayOfMonth()));
//		fileName = year.concat(month).concat(day).concat("-").concat(file.getOriginalFilename());
//		return fileRepository.saveOrUpdate(file, fileName);
//	};
}
