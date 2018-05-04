package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseTypeRoleMap;

public interface CaseTypeRoleMapService {
	
	public List<CaseTypeRoleMap> findAll();
	public CaseTypeRoleMap create(CaseTypeRoleMap item);
	public CaseTypeRoleMap findById(Integer caseSubCaseId , Integer stage, Integer roleId);
	public  List<CaseTypeRoleMap> findByRoleId(Integer roleId);
	public  List<CaseTypeRoleMap> findByCaseSubCaseId(Integer caseSubCaseId);
	public CaseTypeRoleMap update(CaseTypeRoleMap item);
	public  List<CaseTypeRoleMap> findByStageCaseSubCaseId (Integer caseSubCaseId , Integer stage);


}
