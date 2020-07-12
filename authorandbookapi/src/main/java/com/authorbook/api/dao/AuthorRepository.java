package com.authorbook.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.authorbook.api.model.Author;

 

public interface AuthorRepository extends MongoRepository<Author,Integer> {
	public Author findByUserName(String username);
}
