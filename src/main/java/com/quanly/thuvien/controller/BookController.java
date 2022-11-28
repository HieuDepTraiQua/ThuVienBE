package com.quanly.thuvien.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.FileInfo;
import com.quanly.thuvien.reponse.ResponseMessage;
import com.quanly.thuvien.service.BookService;
import com.quanly.thuvien.service.PhotoService;

@RestController
@RequestMapping("/api/thuvien/book")
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PhotoService photoService;
	
	private final Path root = Paths.get("uploads");
	
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
	
	@PostMapping()
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
	public ResponseEntity<?> uploadImage(@RequestParam("files") MultipartFile files) {
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
	
	@PostMapping("/upload")
	  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    Map<String, Object> response = new HashMap<>();
	    try {
	      photoService.save(file);
	      String url = MvcUriComponentsBuilder
		          .fromMethodName(BookController.class, "getFile", file.getOriginalFilename().toString()).build().toString();
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      response.put("success", true);
	      response.put("message", message);
	      response.put("url", url);
	      return new ResponseEntity<>(response, HttpStatus.OK);
//	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	
	  @GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = photoService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(BookController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }
	
	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable(value = "filename") String filename) {
	    Resource file = photoService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
}
