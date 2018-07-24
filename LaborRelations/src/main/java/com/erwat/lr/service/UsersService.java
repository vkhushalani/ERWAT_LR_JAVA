package com.erwat.lr.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import com.erwat.lr.model.Users;

public interface UsersService {
	
	public List<Users> findAll();
	public JSONArray findAllSF(String searchString);
	public Users update(Users item);
	public Users create(Users item);
//	public Users findById(String id);
	public Users findByIdFromSF(String id);
	public JSONArray findMultipleIdFromSF(ArrayList<String> userIDList);
	public void deleteById(String id);
	public List<Users> findByType(String type);

}
