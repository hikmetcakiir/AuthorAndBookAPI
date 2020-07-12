package com.authorbook.api;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorbook.api.model.Book;
import com.authorbook.api.service.BookService;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
 
	@PostMapping(value="/addBook" ,headers="Accept=application/json")
	public void saveBook(@RequestBody Book book) {
		int min=10,max=20000000;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1); 
		book.setId(randomNum);		
		bookService.saveBook(book);
	}
	
	@GetMapping("/findAllBooks")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBook(@PathVariable int id){
		return bookService.getBook(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteBook(@PathVariable int id) {
		  bookService.deleteBook(id);
	}
	
	@GetMapping("/findByName/{name}")
	public Book getFindByName(@PathVariable String name) {
		return bookService.getFindByName(name); 
	}
	
	@GetMapping("/findByPageNumber/{pageNumber}")
	public List<Book> getFindByPageNumber(@PathVariable int pageNumber) {
		return bookService.getFindByPageNumber(pageNumber); 
	}
	
	@GetMapping("/findByType/{type}")
	public List<Book> getFindByType(@PathVariable String type) {
		return bookService.getFindByType(type); 
	}
	
	@GetMapping("/findByPublicationYear/{publicationYear}")
	public List<Book> getFindByPublicationYear(@PathVariable int publicationYear) {
		return bookService.getFindByPublicationYear(publicationYear); 
	}
	
	@GetMapping("/updateById/{id}")
	public void updateById(@PathVariable int id,@RequestBody Book book) {
		  bookService.updateById(id, book);
	}
	
	 
	
}
