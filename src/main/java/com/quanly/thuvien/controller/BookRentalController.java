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

import com.quanly.thuvien.dto.BookRentalDTO;
import com.quanly.thuvien.model.BookRentalModel;
import com.quanly.thuvien.model.StaffModel;
import com.quanly.thuvien.repository.BookRentalRepository;
import com.quanly.thuvien.service.BookRentalService;

@RestController
@RequestMapping("/api/thuvien/rental")
@Controller
public class BookRentalController {
	@Autowired
	private BookRentalService bookRentalService;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAllProduct() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<BookRentalModel> data = bookRentalService.getAll();
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
	public ResponseEntity<?> create(@RequestBody BookRentalModel rental) {
		Map<String, Object> response = new HashMap<>();
		try {
			BookRentalModel data = bookRentalService.create(rental);
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
			bookRentalService.deleteById(id);
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
	public ResponseEntity<?> update(@RequestBody BookRentalModel rental, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			BookRentalModel data = bookRentalService.update(rental, id);
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
			Page<BookRentalDTO> data = bookRentalService.get(paging);
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
