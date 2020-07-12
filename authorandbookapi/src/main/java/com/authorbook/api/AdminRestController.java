package com.authorbook.api;

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
import org.springframework.web.bind.annotation.RestController;

import com.authorbook.api.model.Admin;
import com.authorbook.api.model.Author;
import com.authorbook.api.model.Book;
import com.authorbook.api.service.AdminService;
import com.authorbook.api.service.AuthorService;
import com.authorbook.api.service.BookService;
 
@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/addAdmin")
	public void saveAdmin(@RequestBody Admin admin) {
 	
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Admin orjAdmin = adminService.getFindByUserName(authentication.getName());
	 
		int min=10,max=20000000;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1); 
		admin.setId(randomNum);
		boolean compVal = false;
		 
		List<Admin> admins = adminService.getAdmins();
		int counter = 0;
		if(!admins.isEmpty()) {
			while(compVal == false) {
			for(Admin adminObj : admins) {
				if(adminObj.getId() == admin.getId()) {
					counter = 0;
					randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					admin.setId(randomNum);
					break;
					}
				counter++;
				}
			if(counter == admins.size())
				compVal=true;
			}
		}
		
		  adminService.saveAdmin(admin); 
	}
	
	@GetMapping("/profil")
	public Admin profil(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return adminService.getFindByUserName(authentication.getName());
	}
	
	@GetMapping("/findByAdminName/{name}")
	public Admin getFindByAdminName(@PathVariable String name) {
		return adminService.getFindByUserName(name); 
	}
	
	@GetMapping("/findAllAdmins")
	public List<Admin> getAdmins(){
		return 	adminService.getAdmins();
	}
	
	@GetMapping("/findAllAdmins/{id}")
	public Optional<Admin> getAdmin(@PathVariable int id){
		return 	adminService.getAdmin(id);
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	public void deleteAdmin(@PathVariable int id) {
		  adminService.deleteAdmin(id);
	}
	@PostMapping("/update")
	public void update(@RequestBody Admin admin) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Admin findAdmin = adminService.getFindByUserName(authentication.getName());
		int adminId = findAdmin.getId();
		
		if(admin.getName() == null) admin.setName(findAdmin.getName());
		if(admin.getPassword() == null) admin.setPassword(findAdmin.getPassword());
		if(admin.getUserName() == null) admin.setUserName(findAdmin.getUserName());
		if(admin.getSurname() == null) admin.setSurname(findAdmin.getSurname());
	 
	    adminService.updateById(adminId,admin);
	}
	 
	
	/*
	 * Author Operations
	 * 
	 */
	@PostMapping("/addAuthor")
	public void saveAuthor(@RequestBody Author author) {
		
		int min=10,max=20000000;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1); 
		author.setId(randomNum);
		boolean compVal = false;
		 
		List<Author> authors = authorService.getAuthors();
		int counter = 0;
		if(!authors.isEmpty()) {
			while(compVal == false) {
			for(Author authorObj : authors) {
				if(authorObj.getId() == author.getId()) {
					counter = 0;
					randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					author.setId(randomNum);
					break;
					}
				counter++;
				}
			if(counter == authors.size())
				compVal=true;
			}
		}
		System.out.println("author information : "+author);
		authorService.saveAuthor(author); 
	}
	
	@GetMapping("/findAllAuthors")
	public List<Author> getAuthors(){
		return 	authorService.getAuthors();
	}
	
	@GetMapping("/findAllAuthors/{id}")
	public Optional<Author> getAuthor(@PathVariable int id){
		return 	authorService.getAuthor(id);
	}
	
	@DeleteMapping("/deleteAuthor/{id}")
	public void deleteAuthor(@PathVariable int id) {
		  authorService.deleteAuthor(id);
	}
	@GetMapping("/updateByIdAuthor/{id}")
	public void updateByIdAuthor(@PathVariable int id,@RequestBody Author author) {
		  authorService.updateById(id,author);
	}
	
	@GetMapping("/findByAuthorName/{name}")
	public Author getFindByAuthorName(@PathVariable String name) {
		return authorService.getFindByName(name); 
	}
	
	/*
	 * 
	 * 
	 * Book Operations
	 * 
	 */

	@PostMapping("/addBook")
	public void saveBook(@RequestBody Book book) {
		

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
		System.out.println("author information : "+book);
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
	
	@DeleteMapping("/deleteBook/{id}")
	public void deleteBook(@PathVariable int id) {
		  bookService.deleteBook(id);
	}
	
	@GetMapping("/findByBookName/{name}")
	public Book getFindByBookName(@PathVariable String name) {
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
	
	@PostMapping("/updateByIdBook/{id}")
	public void updateByIdBook(@PathVariable int id,@RequestBody Book book) {
		  bookService.updateById(id, book);
	}
	
	
}
