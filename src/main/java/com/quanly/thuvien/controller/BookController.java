package com.quanly.thuvien.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.CustomerModel;
import com.quanly.thuvien.repository.BookRepository;
import com.quanly.thuvien.service.BookService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/thuvien/book")
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable paging = PageRequest.of(page, size);
			Page<BookDTO> data = bookService.get(paging);
			response.put("data", data.getContent());
			response.put("totalRecord", data.getTotalElements());
			response.put("totalPage", data.getTotalPages());
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	
	@PostMapping("/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> create(@RequestBody BookModel book) {
		Map<String, Object> response = new HashMap<>();
		try {
			BookModel data = bookService.create(book);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			bookService.deleteById(id);
			response.put("success", true);
			response.put("message", "Delete successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	
	@PutMapping("/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> update(@RequestBody BookModel book, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			BookModel data = bookService.update(book, id);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Update successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	
	@GetMapping("/getall")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAllBook() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<BookModel> data = bookService.getAll();
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	@PostMapping("/uploads")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> uploadImage(@RequestBody MultipartFile files) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			String filePath = bookService.uploadPhoto(files, null);
			response.put("data", filePath);
			response.put("success", true);
			response.put("message", "Upload file successfuly !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	
}
