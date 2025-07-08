package com.Diwise_InBlogsAndContacts.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Blogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bId;
	private String title;
	private String description;
	private String featuredImage;
	private String category;
	private String date;
}
