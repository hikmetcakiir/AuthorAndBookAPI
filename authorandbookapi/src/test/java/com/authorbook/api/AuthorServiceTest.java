package com.authorbook.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

 
import com.authorbook.api.model.Author;
import com.authorbook.api.service.AuthorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

	@Autowired
	private AuthorService authorService;
	
	
	/*
	 * Author Service Test
	 * 
	 * 
	 * 
	 */
	@Test
	public void testFindAuthors() {
		 List<Author> authors = authorService.getAuthors();
		 int auhorSize=authors.size();
		 assertEquals(7,auhorSize); 
	}
	
	
	@Test
	public void testGetAdmin() {
		 Author author =  (authorService.getAuthor(6765)).get();
		 assertEquals(6765,author.getId()); 
	}
	
	@Test
	public void testGetFindByUserName() {
		 Author author =  authorService.getFindByName("hkmtckr");
		 assertEquals( author.getUserName(),"hkmtckr"); 
	}
	
	@Test
	public void testSaveAdmin() {
		 Author author = new Author();
		 author.setId(10);
		 author.setName("junitTest2");
		 author.setPassword("junitTestPassword2");
		 author.setSurname("junitTestSurname2");
		 author.setUserName("junitTestUsername2");
		 authorService.saveAuthor(author);
		 Author searchAdmin=authorService.getFindByName("junitTestUsername2");
		 assertEquals(searchAdmin.getId(),author.getId()); 
	}
 
	@Test
	public void testDeleteAdmin() {
		 Author author = new Author();
		 author.setId(10);
		 author.setName("junitDeleteTest");
		 author.setPassword("junitTestDeletePassword");
		 author.setSurname("junitTestDeleteSurname");
		 author.setUserName("junitTestDeleteUsername");
		 authorService.saveAuthor(author);
		 
		 authorService.deleteAuthor(author.getId());
		 Author searchAdmin=authorService.getFindByName("junitTestDeleteUsername");
		 
		 assertEquals( searchAdmin , null); 
	}
	
	@Test
	public void testUpdateAdmin() {
		Author author = new Author();
		author.setId(120);
		author.setName("junitUpdateTest");
		author.setPassword("junitTestUpdatePassword");
		author.setSurname("junitTestUpdateSurname");
		author.setUserName("junitTestUpdateUsername");
		authorService.saveAuthor(author);
		Author searchAuthor=authorService.getFindByName("junitTestUpdateUsername");
		searchAuthor.setUserName("NEWjunitTestUpdateUsername");
		 authorService.updateById(searchAuthor.getId(),searchAuthor);
		 assertEquals( "NEWjunitTestUpdateUsername"  ,  searchAuthor.getUserName()); 
	}
	
}
