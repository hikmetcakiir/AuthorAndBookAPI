package com.authorbook.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.authorbook.api.model.Admin;
import com.authorbook.api.model.Author;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String tempUsername=username;
		String password="java";
		String role="ADMIN";
		boolean connection = false;
		
		try{
			Admin admin = adminService.getFindByUserName(username);
			System.out.println("admin : "+admin);
			username=admin.getUserName();
			if(admin != null) {
				password=admin.getPassword();
				role="ADMIN";
				connection=true;
			}
			if(admin == null)
				username = tempUsername;
		}catch(Exception ex) {
			System.out.println("Error!");
		}
			
		
		System.out.println("username : "+username);
		
		try{
			Author author = authorService.getFindByName(username);
			System.out.println("author : "+author);
			if(author != null) {
				username=author.getUserName();
				password=author.getPassword();
				role="AUTHOR";
				connection=true;
			}
		}catch(Exception ex) {
			System.out.println("Error!");
		}
		
		if(connection != true) {
			System.out.println("Connection değerleri yok");
			username="java";
		}
		System.out.println("username :"+username+" password : "+password+" role : "+role);
		return User
					.withUsername(username)
					.password(passwordEncoder.encode(password))
					.roles(role)
					.build();
					
	}
	
}
