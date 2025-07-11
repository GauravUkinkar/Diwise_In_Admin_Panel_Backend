package com.Diwise_InBlogsAndContacts.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Diwise_InBlogsAndContacts.Dto.BlogsDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Service.BlogsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/blog")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class BloController {
	private final BlogsService blogService;
	
	
	@PostMapping(value="/addblog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Message<BlogsDto>> uploadBlog(@RequestPart("blog") String BlogsDtojson,
			@RequestPart(value = "featureimage", required = false) MultipartFile featureimage) 
	throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		BlogsDto dto = objectMapper.readValue(BlogsDtojson, BlogsDto.class);
		Message<BlogsDto> message = blogService.addBlogs(dto, featureimage);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}	
	@PutMapping(value="/updateblog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Message<BlogsDto>> updateBlog(@RequestPart("blog") String BlogDtojson,
			@RequestPart(value="featureimage", required = false) MultipartFile featureimage) 
	throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		BlogsDto dto = objectMapper.readValue(BlogDtojson, BlogsDto.class);
		Message<BlogsDto> message = blogService.updateBlog(dto, featureimage);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@DeleteMapping("/deleteblog")
	public ResponseEntity<Message<BlogsDto>> deleteBlog(@RequestParam("bId") int bId) {
		Message<BlogsDto> message = blogService.deleteBlog(bId);
		log.info("Blog deleted successfully");
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getBybId")
	public ResponseEntity<Message<BlogsDto>> getBlogBybId(@RequestParam("bId") int bId) {
			Message<BlogsDto> message = blogService.getBybId(bId);
			HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
			return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getallblogs")
	public ResponseEntity<List<Message<BlogsDto>>> getAllBlogs() {
		List<Message<BlogsDto>> message = blogService.getAllBlogs();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	@GetMapping("/getblogbycategory")
	public ResponseEntity<Message<List<BlogsDto>>> getBlogByCategory(@RequestParam("category") String category) {
		Message<List<BlogsDto>> message = blogService.getBlogsByCategory(category);		
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
