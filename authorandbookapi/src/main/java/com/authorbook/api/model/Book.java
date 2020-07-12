package com.authorbook.api.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="book") 
public class Book {
	@Id
	private int id;
	private int authorId;
	private int pageNumber;
	private String publicationYear;
	private String name;
	private String summary;
	private String type;
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", authorId=" + authorId + ", pageNumber=" + pageNumber + ", publicationYear="
				+ publicationYear + ", name=" + name + ", summary=" + summary + ", type=" + type + "]";
	}
	
	
	
	public Book() {
		super();
	}



	public Book(int id, int authorId, int pageNumber, String publicationYear, String name, String summary,
			String type) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.pageNumber = pageNumber;
		this.publicationYear = publicationYear;
		this.name = name;
		this.summary = summary;
		this.type = type;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}  
	
	
}
