package com.authorbook.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorbook.api.model.Author;
import com.authorbook.api.model.Book;
import com.authorbook.api.service.BookService;

 

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	

	/*
	 * Book Service Test
	 * 
	 * 
	 * 
	 */
	@Test
	public void testFindBooks() {
		 List<Book> books = bookService.getBooks();
		 int bookSize=books.size();
		 assertEquals(5,bookSize); 
	}
	
	
	@Test
	public void testGetBook() {
		Book book =  (bookService.getBook(2)).get();
		 assertEquals(2,book.getId()); 
	}
	
	@Test
	public void testGetFindByName() {
		Book book = bookService.getFindByName("GizemliDev");
		 assertEquals( book.getName(),"GizemliDev"); 
	}
	
	 
	@Test
	public void testSaveBook() {
		 Book book = new Book();
		 book.setId(10);
		 book.setName("junitTest2");
 
		 bookService.saveBook(book);
		 Book searchBook=bookService.getFindByName("junitTest2");
		 assertEquals(searchBook.getId(),book.getId()); 
	}
 
	@Test
	public void testDeleteBook() {
		Book book = new Book();
		book.setId(10);
		book.setName("junitDeleteTest");
	 
		bookService.saveBook(book);
		 
		bookService.deleteBook(book.getId());
		Book searchBook=bookService.getFindByName("junitDeleteTest");
		 
		 assertEquals( searchBook , null); 
	}
	
}
