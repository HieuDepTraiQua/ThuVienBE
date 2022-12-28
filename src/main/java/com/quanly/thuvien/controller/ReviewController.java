package com.quanly.thuvien.controller;

import com.quanly.thuvien.dto.ReviewDTO;
import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.ReviewModel;
import com.quanly.thuvien.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thuvien/review")
@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getall")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ReviewModel> data = reviewService.getAll();
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
    public ResponseEntity<?> create(@RequestBody ReviewModel review) {
        Map<String, Object> response = new HashMap<>();
        try {
            ReviewModel data = reviewService.create(review);
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
            reviewService.deleteById(id);
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
    public ResponseEntity<?> update(@RequestBody ReviewModel category, @PathVariable(value = "id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ReviewModel data = reviewService.update(category, id);
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

    @GetMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam() String bookName) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<ReviewDTO> data = reviewService.get(paging, bookName);
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
