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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanly.thuvien.model.LibraryCardModel;
import com.quanly.thuvien.repository.LibraryCardRepository;
import com.quanly.thuvien.service.LibraryCardService;

@RestController
@RequestMapping("/api/thuvien/card")
@Controller
public class LibraryCardController {

	@Autowired
	private LibraryCardRepository cardRepository;
	
	@Autowired
	private LibraryCardService cardService;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAllProduct() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<LibraryCardModel> data = cardService.getAll();
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
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> create(@RequestBody LibraryCardModel card) {
		Map<String, Object> response = new HashMap<>();
		try {
			LibraryCardModel data = cardService.create(card);
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
	
	@DeleteMapping("/delete/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			cardService.deleteById(id);
			response.put("success", true);
			response.put("message", "Delete successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
	
	@PutMapping("/update/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> update(@RequestBody LibraryCardModel card, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			LibraryCardModel data = cardService.update(card, id);
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
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable paging = PageRequest.of(page, size);
			Page<LibraryCardModel> data = cardService.get(paging);
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
}
