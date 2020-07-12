package com.authorbook.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
 

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorbook.api.model.Admin;
import com.authorbook.api.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
	/*
	 * Admin Service Test
	 * 
	 * 
	 * 
	 */
 
	@Test
	public void testGetAdmin() {
		 Admin admin =  (adminService.getAdmin(1)).get();
		 assertEquals(1,admin.getId() ); 
	}
	
	@Test
	public void testGetFindByUserName() {
		 Admin admin =  adminService.getFindByUserName("admin");
		 assertEquals( admin.getUserName(),"admin"); 
	}
	@Test
	public void testFindAdmins() {
		 List<Admin> admins = adminService.getAdmins();
		 int adminSize=admins.size();
		 assertEquals(adminSize,3); 
	}
	@Test
	public void testSaveAdmin() {
		 Admin admin = new Admin();
		 admin.setId(10);
		 admin.setName("junitTest2");
		 admin.setPassword("junitTestPassword2");
		 admin.setSurname("junitTestSurname2");
		 admin.setUserName("junitTestUsername2");
		 adminService.saveAdmin(admin);
		 Admin searchAdmin=adminService.getFindByUserName("junitTestUsername2");
		 assertEquals(searchAdmin.getId(),admin.getId()); 
	}
	
	@Test
	public void testDeleteAdmin() {
		 Admin admin = new Admin();
		 admin.setId(10);
		 admin.setName("junitDeleteTest");
		 admin.setPassword("junitTestDeletePassword");
		 admin.setSurname("junitTestDeleteSurname");
		 admin.setUserName("junitTestDeleteUsername");
		 adminService.saveAdmin(admin);
		 
		 adminService.deleteAdmin(admin.getId());
		 Admin searchAdmin=adminService.getFindByUserName("junitTestDeleteUsername");
		 
		 assertEquals( searchAdmin , null); 
	}
	
	@Test
	public void testUpdateAdmin() {
		 Admin admin = new Admin();
		 admin.setId(120);
		 admin.setName("junitUpdateTest");
		 admin.setPassword("junitTestUpdatePassword");
		 admin.setSurname("junitTestUpdateSurname");
		 admin.setUserName("junitTestUpdateUsername");
		 adminService.saveAdmin(admin);
		 Admin searchAdmin=adminService.getFindByUserName("junitTestUpdateUsername");
		 searchAdmin.setUserName("NEWjunitTestUpdateUsername");
		 adminService.updateById(searchAdmin.getId(),searchAdmin);
		 assertEquals( "NEWjunitTestUpdateUsername"  ,  searchAdmin.getUserName()); 
	}
}
