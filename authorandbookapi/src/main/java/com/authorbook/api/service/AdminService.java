package com.authorbook.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authorbook.api.dao.AdminRepository; 
import com.authorbook.api.model.Admin;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;
	

	public void saveAdmin(Admin admin) {
		repository.save(admin); 
	}

	public List<Admin> getAdmins(){
		return repository.findAll();
	}
	

	public Optional<Admin> getAdmin(int id){
		return repository.findById(id);
	}
	

	public void deleteAdmin(int id) {
		  repository.deleteById(id);
	}
	
	public void updateById(int id,Admin admin) {
		admin.setId(id);
		repository.deleteById(id);
		repository.save(admin);
	}
	
	public Admin getFindByUserName(String username) {
		return repository.findByUserName(username); 
	}

}
