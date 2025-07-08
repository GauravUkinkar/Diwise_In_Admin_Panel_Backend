package com.Diwise_InBlogsAndContacts.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Diwise_InBlogsAndContacts.Dto.BlogsDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;

public interface BlogsService {
 public Message<BlogsDto> addBlogs(BlogsDto blogsDto,MultipartFile images);
 public List<Message<BlogsDto>> getAllBlogs();
 public Message<List<BlogsDto>> getBlogsByCategory(String category);
 public Message<BlogsDto> getBybId(int bId);
 public Message<BlogsDto> deleteBlog(int bId);
 public Message<BlogsDto> updateBlog(BlogsDto request,MultipartFile images);
}
