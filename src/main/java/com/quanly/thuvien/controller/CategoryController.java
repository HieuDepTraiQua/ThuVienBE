package com.quanly.thuvien.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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

import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.service.CategoryService;

@RestController
@RequestMapping("/api/thuvien/category")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/getall")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAllProduct() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<CategoryModel> data = categoryService.getAll();
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
	
	@PostMapping()
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> create(@RequestBody CategoryModel category) {
		Map<String, Object> response = new HashMap<>();
		try {
			CategoryModel data = categoryService.create(category);
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
			categoryService.deleteById(id);
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
	public ResponseEntity<?> update(@RequestBody CategoryModel category, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			CategoryModel data = categoryService.update(category, id);
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
	
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	@CrossOrigin(origins = "*", maxAge = 3600)
//	public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Pageable paging = PageRequest.of(page, size);
//			Page<CategoryModel> data = categoryService.get(paging);
//			response.put("data", data.getContent());
//			response.put("totalRecord", data.getTotalElements());
//			response.put("totalPage", data.getTotalPages());
//			response.put("success", true);
//			response.put("message", "Ok");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("success", false);
//			response.put("message", e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//	};
	
	@GetMapping("")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String keyword) {
		Map<String, Object> response = new HashMap<>();
		try {
			PagedListHolder<CategoryModel> data = categoryService.get(page, size, keyword);
			response.put("data", data.getPageList());
			response.put("totalRecord", data.getSource().size());
			response.put("totalPage", data.getPageCount());
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
