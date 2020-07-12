package com.authorbook.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 
import com.authorbook.api.model.Author;
import com.authorbook.api.model.Book;
import com.authorbook.api.service.AuthorService;
import com.authorbook.api.service.BookService;

@RestController
@RequestMapping("/author")
public class AuthorRestController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
		
	@GetMapping("/mybooks")
	public List<Book> getAuthorsBook() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		List<Book> books = bookService.getBooks();
		List<Book> authorsBookList = new ArrayList<Book>();
		
		Author author = authorService.getFindByName(authentication.getName());
		
		if(authentication!=null) {
			 
			for(Book book : books ) {
				if(book.getAuthorId() == author.getId())
					authorsBookList.add(book);
			}
			 return authorsBookList;
		}
		return null;
	}
	
	@GetMapping("/profil")
	public Author profil(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authorService.getFindByName(authentication.getName());
	}
	
	@GetMapping("/findAllAuthors")
	public List<Author> getAuthors(){
		return 	authorService.getAuthors();
	}
	
	@GetMapping("/findAllAuthors/{id}")
	public Optional<Author> getAuthor(@PathVariable int id){
		return 	authorService.getAuthor(id);
	}
	
	@GetMapping("/findByUserName/{username}")
	public Author getFindByUserName(@PathVariable String username) {
		return authorService.getFindByName(username); 
	}
	
	@PostMapping("/addBook")
	@ResponseBody
	public Book saveBook(@RequestBody Book book) {
		 
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Author author = authorService.getFindByName(authentication.getName());
		book.setAuthorId(author.getId());
			
		int min=10,max=20000000;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1); 
		book.setId(randomNum);
		boolean compVal = false;
		 
		List<Book> books = bookService.getBooks();
		int counter = 0;
		if(!books.isEmpty()) {
			while(compVal == false) {
			for(Book bookObj : books) {
				if(bookObj.getId() == book.getId()) {
					counter = 0;
					randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					book.setId(randomNum);
					break;
					}
				counter++;
				}
			if(counter == books.size())
				compVal=true;
			}
		}
		
	   bookService.saveBook(book);
		return book;
	}
	
	
	@PostMapping("/update")
	public void updateAuthor(@RequestBody Author author) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Author findAuthor = authorService.getFindByName(authentication.getName());
		int authorId = findAuthor.getId();
		
		if(author.getName() == null) author.setName(findAuthor.getName());
		if(author.getPassword() == null) author.setPassword(findAuthor.getPassword());
		if(author.getUserName() == null) author.setUserName(findAuthor.getUserName());
		if(author.getSurname() == null) author.setSurname(findAuthor.getSurname());
		if(author.getBirthday() == null) author.setBirthday(findAuthor.getBirthday());
	 
		System.out.println("author information : "+author);
		authorService.updateById(authorId,author);
	}
	
	@DeleteMapping("/deleteMyBook/{bookId}")
	public void deleteBook(@PathVariable int bookId) {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		List<Book> books = bookService.getBooks();
		List<Book> authorsBookList = new ArrayList<Book>();
		
		Author author = authorService.getFindByName(authentication.getName());
		
		if(authentication != null) {
			for(Book book : books ) {
				if(book.getAuthorId() == author.getId())
					authorsBookList.add(book);
			}
		}
		
		int authorsBookListSize = authorsBookList.size();
		for(int i = 0 ; i < authorsBookListSize ; i++ ) {
			if(bookId == authorsBookList.get(i).getId()) {
				  bookService.deleteBook(bookId);	
			}
		}
		
	 
	}
	 
}
