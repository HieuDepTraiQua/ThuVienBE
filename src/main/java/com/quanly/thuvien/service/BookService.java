package com.quanly.thuvien.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.quanly.thuvien.repository.FileRepository;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	public FileRepository fileRepository;

	public BookService() {
		super();
		fileRepository = new FileRepository("book");
	}


	public BookModel create(BookModel book) {
		Optional<BookModel> opBook  = bookRepository.findByName(book.getNameBook());
		if (opBook.isPresent()) {
			throw new EntityNotFoundException("Book name is exist!");
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
			if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
				book.setAuthor(optional.get().getAuthor());
			}
			if (book.getCategoryId() == null || book.getCategoryId().isEmpty()) {
				book.setCategoryId(optional.get().getCategoryId());
			}
			if (book.getPublishYear() == null || book.getPublishYear().isEmpty()) {
				book.setPublishYear(optional.get().getPublishYear());
			}
			if (book.getPageOfBook() == null || book.getPageOfBook().isEmpty()) {
				book.setPageOfBook(optional.get().getPageOfBook());
			}
			if (book.getDescription() == null || book.getDescription().isEmpty()) {
				book.setDescription(optional.get().getDescription());
			}
			if (book.getPrice() == null || book.getPrice().isEmpty()) {
				book.setPrice(optional.get().getPrice());
			}
			if (book.getRemainingStock() == null || book.getRemainingStock().isEmpty()) {
				book.setRemainingStock(optional.get().getRemainingStock());
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
			BookDTO dto = getBookDto(book);
			listDto.add(dto);
		}
		return listDto;
	};

	public BookDTO getBookDto(BookModel book) {
		BookDTO dto = new BookDTO();
		Optional<CategoryModel> opCate = categoryRepository.findById(book.getCategoryId());
		if (!opCate.isPresent()) {
			throw new EntityNotFoundException("Category Id not found!");
		}
		dto.setCategoryTitle(opCate.get().getTitle());
		BeanUtils.copyProperties(book, dto);
		return dto;
	};

	public String uploadPhoto(MultipartFile file, String title) throws Exception {

		if (!file.getContentType().startsWith("image")) {
			throw new EntityNotFoundException("Upload file is not image!");
		}
		String fileName = "";
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String month = localDate.getMonthValue() >= 10 ? String.valueOf(localDate.getMonthValue())
				: ("0" + localDate.getMonthValue());
		String year = String.valueOf(localDate.getYear());
		String day = localDate.getDayOfMonth() >= 10 ? String.valueOf(localDate.getDayOfMonth())
				: ("0" + (localDate.getDayOfMonth()));
		fileName = year.concat(month).concat(day).concat("-").concat(file.getOriginalFilename());
		return fileRepository.saveOrUpdate(file, fileName);
	};
}
