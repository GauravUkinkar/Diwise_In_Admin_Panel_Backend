package com.Diwise_InBlogsAndContacts.Dto;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogsDto {
	private int bId;
	private String title;
	private String description;
	private String featuredImage;
	private String category;
	private String date;
}
