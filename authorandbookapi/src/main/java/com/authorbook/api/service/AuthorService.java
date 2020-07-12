package com.authorbook.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.authorbook.api.dao.AuthorRepository;
import com.authorbook.api.model.Author;

 
@Service
public class AuthorService {
		
		@Autowired
		private AuthorRepository repository;
		

		public void saveAuthor(Author author) {
			repository.save(author);	 
		}

		public List<Author> getAuthors(){
			return repository.findAll();
		}
		

		public Optional<Author> getAuthor(int id){
			return repository.findById(id);
		}
		

		public void deleteAuthor(int id) {
			  repository.deleteById(id);
		}
		
		public void updateById(int id,Author author) {
			author.setId(id);
			
			repository.deleteById(id);
			repository.save(author);
 
		}
		
		public Author getFindByName(String username) {
			return repository.findByUserName(username); 
		}
	
}
