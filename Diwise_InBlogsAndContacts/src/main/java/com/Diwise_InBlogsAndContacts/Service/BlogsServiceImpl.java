package com.Diwise_InBlogsAndContacts.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.Diwise_InBlogsAndContacts.Dto.BlogsDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Repository.BlogsRepository;
import com.Diwise_InBlogsAndContacts.Util.Constants;
import com.Diwise_InBlogsAndContacts.model.Blogs;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BlogsServiceImpl implements BlogsService {
	private final BlogsRepository blogsRepository;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	@Override
	public Message<BlogsDto> addBlogs(BlogsDto blogsDto,MultipartFile images) {
		Message<BlogsDto> message = new Message<>();
		log.info("In BlogsServiceImpl addBlogs() with request: {}", blogsDto);
		Blogs blog=new Blogs();
		try {
			blog.setTitle(blogsDto.getTitle());
			blog.setDescription(blogsDto.getDescription());
			blog.setCategory(blogsDto.getCategory());
			blog.setDate(blogsDto.getDate());
			blog.setFeaturedImage(uploadImage(images, images.getOriginalFilename()));
			blogsRepository.save(blog);
			blogsDto.setFeaturedImage(blog.getFeaturedImage());
			message.setData(blogsDto);
			message.setResponseMessage(Constants.BLOG_ADDED_SUCCESSFULLY);
			message.setStatus(HttpStatus.CREATED);
			return message;
		} catch (Exception e) {
			throw new RuntimeException(Constants.SOMETHING_WENT_WRONG, e);
		}
	}
	@Override
	public Message<BlogsDto> updateBlog(BlogsDto request,MultipartFile images) {
		 Message<BlogsDto> message = new Message<>();
		    try {
		        Blogs blog = blogsRepository.findById(request.getBId()).orElse(null);

		        if (blog != null) {
		            boolean isOnlyImageUpdate = (request.getTitle() == null || request.getTitle().isBlank())
		                    && (request.getCategory() == null || request.getCategory().isBlank())
		                    && (request.getDescription() == null || request.getDescription().isBlank())
		                    && (request.getDate() == null);
		            if (images != null && !images.isEmpty()) {
		                blog.setFeaturedImage(uploadImage(images, images.getOriginalFilename()));
		            }
		            if (!isOnlyImageUpdate) {
		                blog.setTitle(request.getTitle());
		                blog.setCategory(request.getCategory());
		                blog.setDescription(request.getDescription());
		                blog.setDate(request.getDate());
		            }
		            blogsRepository.save(blog);

		            request.setBId(blog.getBId());
		            request.setFeaturedImage(blog.getFeaturedImage());
		           

		            message.setData(request);
		            message.setResponseMessage(Constants.BLOG_UPDATED);
		            message.setStatus(HttpStatus.CREATED);
		            return message;
		        } else {
		            message.setResponseMessage(Constants.BLOG_NOT_FOUND);
		            message.setStatus(HttpStatus.NOT_FOUND);
		            return message;
		        }
		    } catch (Exception e) {
		        throw new RuntimeException(Constants.SOMETHING_WENT_WRONG, e);
		    }
	}

	@Override
	public List<Message<BlogsDto>> getAllBlogs() {
		List<Message<BlogsDto>> messages = new ArrayList<>();
		List<Blogs> blogs = blogsRepository.findAll();
		for (Blogs blog : blogs) {
			BlogsDto dto = new BlogsDto();
			dto.setBId(blog.getBId());
			dto.setCategory(blog.getCategory());
			dto.setDate(blog.getDate());
			dto.setDescription(blog.getDescription());
			dto.setFeaturedImage(blog.getFeaturedImage());
			dto.setTitle(blog.getTitle());
			Message<BlogsDto> message = new Message<>();
			message.setData(dto);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.BLOGS_FETCHED);
			messages.add(message);
		}
		return messages;
	}

	@Override
	 public Message<List<BlogsDto>> getBlogsByCategory(String category){
		Message<List<BlogsDto>> message = new Message<>();
		try {
			List<Blogs> blogs = blogsRepository.findBycategory(category);
			if (blogs != null && !blogs.isEmpty()) {
				List<BlogsDto> dtoList = new ArrayList<>();
				for (Blogs blog : blogs) {
					BlogsDto dto = new BlogsDto();
					dto.setBId(blog.getBId());
					dto.setCategory(blog.getCategory());
					dto.setDate(blog.getDate());
					dto.setDescription(blog.getDescription());
					dto.setFeaturedImage(blog.getFeaturedImage());
					dto.setTitle(blog.getTitle());
					dtoList.add(dto);
				}
				message.setData(dtoList);
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.BLOGS_FETCHED);
			} else {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(Constants.NO_BLOGS_FOUND + category);
			}
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
		}
	
		
		return message;
	}

	@Override
	public Message<BlogsDto> getBybId(int bId) {
		Message<BlogsDto> message = new Message<>();
		try {
			Blogs blog = blogsRepository.findById(bId).orElse(null);
			if (blog != null) {
				BlogsDto dto = new BlogsDto();
				dto.setBId(blog.getBId());
				dto.setCategory(blog.getCategory());
				dto.setDate(blog.getDate());
				dto.setDescription(blog.getDescription());
				dto.setFeaturedImage(blog.getFeaturedImage());
				dto.setTitle(blog.getTitle());
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.BLOGS_FETCHED);
				message.setData(dto);
				return message;
			} else {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(Constants.BLOG_NOT_FOUND);
				return message;
			}
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return message;
		}
	}

	@Override
	public Message<BlogsDto> deleteBlog(int bId) {
		Message<BlogsDto> message = new Message<>();
		try {
			Blogs blog = blogsRepository.findById(bId).orElse(null);
			if(blog != null) {
				
				blogsRepository.delete(blog);
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.BLOG_DELETED);
				return message;
			} else {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(Constants.BLOG_NOT_FOUND);
				return message;
			}
		} catch (Exception e) {
			throw new RuntimeException(Constants.SOMETHING_WENT_WRONG, e);
		}
	}


	
	private String uploadImage(MultipartFile image, String imageName) throws IOException {
	    final long MAX_FILE_SIZE = 2 * 1024 * 1024; 

	    if (image != null && !image.isEmpty()) {
	        if (image.getSize() > MAX_FILE_SIZE) {
	            throw new IOException("File size exceeds 2MB limit.");
	        }

	        String fileName = imageName;
	        Path uploadPath = Paths.get(uploadDirectory);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        Path filePath = uploadPath.resolve(fileName);
	        Files.write(filePath, image.getBytes());
	        return "https://diwise.cloud/DewiseUKblog/" + fileName;
	    }

	    return null;
	}

}
