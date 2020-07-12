package com.authorbook.api.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.authorbook.api.model.Book;

 

public interface BookRepository extends MongoRepository<Book,Integer> {
	public Book findByName(String name);
	public List<Book> findByPageNumber(int pageNumber);
	public List<Book> findByType(String type);
	public List<Book> findByPublicationYear(int publicationYear);
}
