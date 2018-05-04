package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseTypeSubTypeMap;

public interface CaseTypeSubTypeMapService {
	
	public List<CaseTypeSubTypeMap> findAll();
	public CaseTypeSubTypeMap create(CaseTypeSubTypeMap item);
	public  List<CaseTypeSubTypeMap> findByCaseId(Integer caseId);
	public  List<CaseTypeSubTypeMap> findBySubCaseId(Integer subCaseId);
	public  CaseTypeSubTypeMap findByCaseSubCaseId(Integer caseId , Integer subCaseId);
	public  CaseTypeSubTypeMap findById(Integer id);

}
