package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseNature;

public interface CaseNatureService {
	
	public List<CaseNature> findAll();
	public CaseNature update(CaseNature item);
	public CaseNature create(CaseNature item);
	public CaseNature findById(Integer id);
	public void deleteByObject(CaseNature item);
	public void deleteById(Integer id);

}
