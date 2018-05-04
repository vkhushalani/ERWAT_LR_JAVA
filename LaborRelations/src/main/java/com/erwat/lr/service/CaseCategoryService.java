package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseCategory;

public interface CaseCategoryService {
	
	public List<CaseCategory> findAll();
	public CaseCategory update(CaseCategory item);
	public CaseCategory create(CaseCategory item);
	public CaseCategory findById(Integer id);
	public void deleteByObject(CaseCategory item);
	public void deleteById(Integer id);

}
