package com.erwat.lr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_USERS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "Users.findAll", query = "SELECT ep FROM Users ep "),
		@NamedQuery(name = "Users.findByType", query = "SELECT ep FROM Users ep WHERE ep.type = :type ")
		
})
public class Users {
	
	@Id
	@Column(name = "\"USER_ID\"", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "\"USER_NAME\"", columnDefinition = "VARCHAR(64)")
	private String userName;
	
	
	@Column(name = "\"USER_TYPE\"", columnDefinition = "VARCHAR(32)")
	private String type;
	
	@Column(name = "\"ROLE\"", columnDefinition = "VARCHAR(32)")
	private String role;
	
	@Column(name = "\"NAME_TEMP\"", columnDefinition = "VARCHAR(64)")
	private String name;
	
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date edate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}
