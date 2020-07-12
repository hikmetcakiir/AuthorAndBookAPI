package com.authorbook.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.authorbook.api.dao.BookRepository;
import com.authorbook.api.model.Book;

 
@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	

	public void saveBook(Book book) {
		repository.save(book);
		 
	}
	

	public List<Book> getBooks(){
		return repository.findAll();
	}
	

	public Optional<Book> getBook(int id){
		return repository.findById(id);
	}
	

	public void deleteBook(int id) {
		repository.deleteById(id);
	 
	}
	

	public Book getFindByName(String name) {
		return repository.findByName(name); 
	}
	

	public List<Book> getFindByPageNumber(int pageNumber) {
		return repository.findByPageNumber(pageNumber); 
	}
	

	public List<Book> getFindByType(String type) {
		return repository.findByType(type); 
	}
	

	public List<Book> getFindByPublicationYear(int publicationYear) {
		return repository.findByPublicationYear(publicationYear); 
	}
	
	public void updateById(int id,Book book) {
		book.setId(id);
		repository.deleteById(id);
		repository.save(book);
	 
	}
	
}
