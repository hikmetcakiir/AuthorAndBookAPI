package com.authorbook.api.dao;

 
import org.springframework.data.mongodb.repository.MongoRepository;

import com.authorbook.api.model.Admin;
 
 
public interface AdminRepository extends MongoRepository<Admin,Integer>{
	public Admin findByUserName(String username);
}
