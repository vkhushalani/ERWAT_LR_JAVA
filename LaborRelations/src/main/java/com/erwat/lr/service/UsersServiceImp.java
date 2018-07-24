package com.erwat.lr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.SAPControllers.CallingSFAPIs;
import com.erwat.lr.model.Users;

@Transactional
@Component
public class UsersServiceImp implements UsersService {
	Logger logger = LoggerFactory.getLogger(UsersServiceImp.class);
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findAll() {
		Query query = em.createNamedQuery("Users.findAll");
		 List<Users> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public Users update(Users item) {
		em.merge(item);
		return item;	}

	@Override
	@Transactional
	public Users create(Users item) {
		em.persist(item);
		return item;
	}

//	@Override
//	public Users findById(String id) {
//		Users item = em.find(Users.class, id);
//		return item;
//	}

	@Override
	@Transactional
	public void deleteById(String id) {
		Users item = em.find(Users.class, id);
		em.remove(item);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByType(String type) {
		Query query = em.createNamedQuery("Users.findByType")
				.setParameter("type", type);
		 List<Users> items = query.getResultList();

	        return items;
	}

	@Override
	public Users findByIdFromSF(String id) {
		CallingSFAPIs sfCall = new CallingSFAPIs();
		ArrayList<String> userIDList;
		userIDList = new ArrayList<String>();
		userIDList.add(id);
		JSONArray jsonArr = sfCall.callSFAPI(userIDList, "UserDetails");

		if(jsonArr.size() != 0){
		
		JSONObject userObject = (JSONObject) jsonArr.get(0);
		Users user = new Users();
		user.setName(userObject.get("firstName").toString() + " " + userObject.get("lastName").toString());
		user.setId(userObject.get("userId").toString());
		user.setUserName(userObject.get("username").toString());
		return user;
		}
		
		return null;
		
	}
	
	@Override
	public JSONArray findMultipleIdFromSF(ArrayList<String> userIDList) {
		CallingSFAPIs sfCall = new CallingSFAPIs();
	
		JSONArray jsonArr = sfCall.callSFAPI(userIDList, "UserDetails");
		return jsonArr;
//		if(jsonArr.size() != 0){
//		
//		JSONObject userObject = (JSONObject) jsonArr.get(0);
//		Users user = new Users();
//		user.setName(userObject.get("firstName").toString() + " " + userObject.get("lastName").toString());
//		user.setId(userObject.get("userId").toString());
//		user.setUserName(userObject.get("username").toString());
//		return user;
//		}
//		
//		return null;
		
	}

	@Override
	public JSONArray findAllSF(String searchString) {
		CallingSFAPIs sfCall = new CallingSFAPIs();
		ArrayList<String> userIDList;
		userIDList = new ArrayList<String>();
		userIDList.add(searchString);
		JSONArray jsonArr = sfCall.callSFAPI(userIDList, "SearchUser");
		return jsonArr;
	}

}
