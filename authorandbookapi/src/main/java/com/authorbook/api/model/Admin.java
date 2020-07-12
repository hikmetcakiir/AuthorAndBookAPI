package com.authorbook.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="admin") 
public class Admin {
	@Id
	private int id;
	private String name;
	private String surname;
	private String userName;
	private String password;

	
	
	public Admin() {
		super();
	}


	  


	public Admin(int id, String name, String surname, String userName, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.password = password;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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





	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", surname=" + surname + ", userName=" + userName + ", password="
				+ password + "]";
	}

 
	
	
}
