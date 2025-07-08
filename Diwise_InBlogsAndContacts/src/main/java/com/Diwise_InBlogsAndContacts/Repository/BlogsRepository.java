package com.Diwise_InBlogsAndContacts.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Diwise_InBlogsAndContacts.model.Blogs;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Integer> {

	List<Blogs> findBycategory(String category);

}
