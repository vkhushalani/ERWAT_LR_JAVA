package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.SubCaseType;

public interface SubCaseTypeService {
	public List<SubCaseType> findAll();
	public SubCaseType update(SubCaseType caseType);
	public SubCaseType create(SubCaseType caseType);
	public SubCaseType findById(Integer id);
	public void deleteByObject(SubCaseType caseType);
	public void deleteById(Integer id);

}
