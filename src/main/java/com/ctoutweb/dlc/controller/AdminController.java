package com.ctoutweb.dlc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@GetMapping("/users")
	public List<User> getAllUSer(){
		return adminService.getAllUsers();
	}
}
