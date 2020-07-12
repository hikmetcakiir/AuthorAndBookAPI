package com.authorbook.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="author")
public class Author {
	@Id
	private int id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String birthday;
	


	public Author() {
		super();
	}


	public Author(int id, String userName, String password, String name, String surname, String birthday) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getSurname() {
		return surname;
	}




	public void setSurname(String surname) {
		this.surname = surname;
	}




	public String getBirthday() {
		return birthday;
	}




	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "Author [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", birthday=" + birthday + "]";
	}
	 
	
	
	 
	
	
	
}
