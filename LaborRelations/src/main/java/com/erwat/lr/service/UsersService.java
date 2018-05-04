package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.Users;

public interface UsersService {
	
	public List<Users> findAll();
	public Users update(Users item);
	public Users create(Users item);
	public Users findById(String id);
	public void deleteById(String id);
	public List<Users> findByType(String type);

}
