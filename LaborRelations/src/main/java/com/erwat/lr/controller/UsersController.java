package com.erwat.lr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.model.Users;
import com.erwat.lr.service.UsersService;

@RestController
@RequestMapping("/TableMaint")
public class UsersController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/Users")
	public ResponseEntity<List<Users>> getAll(){
		
		List<Users> items = usersService.findAll();
	     return ResponseEntity.ok().body(items);
		
	}
	
	@GetMapping("/Users/{id}")
	 public ResponseEntity <Users> getById(@PathVariable("id") String id) {
		Users item = usersService.findByIdFromSF(id);
		return ResponseEntity.ok().body(item);
	   }
	
	@GetMapping("/Users/{type}")
	 public ResponseEntity <List<Users>> getByType(@PathVariable("type") String type) {
		List<Users> items = usersService.findByType(type);
		return ResponseEntity.ok().body(items);
	   }
	@PostMapping(value = "/Users")
	public ResponseEntity<?> create(@RequestBody Users user)  {
		user =  usersService.create(user);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/Users")
	public ResponseEntity<?> update(@RequestBody Users user)  {
		user =  usersService.update(user);
		 return ResponseEntity.ok().body(successMessage);
		
	}
}
