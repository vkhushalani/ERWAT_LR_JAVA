package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseTypes;

public interface CaseTypeService {
	public List<CaseTypes> findAll();
	public CaseTypes update(CaseTypes caseType);
	public CaseTypes create(CaseTypes caseType);
	public CaseTypes findById(Integer caseId);
	public void deleteByObject(CaseTypes caseType);
	public void deleteById(Integer caseId);

}
