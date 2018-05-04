package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseTypeNatureCategoryMap;

public interface CaseTypeNatureCategoryMapService {
	public List<CaseTypeNatureCategoryMap> findAll();
	public CaseTypeNatureCategoryMap create(CaseTypeNatureCategoryMap item);
	public  CaseTypeNatureCategoryMap findByCaseSubCaseNature(Integer caseSubCaseId,Integer natureId);
	public  CaseTypeNatureCategoryMap findByCaseSubCaseCategory(Integer caseSubCaseId,Integer natureId);
	public  List<CaseTypeNatureCategoryMap> findByCaseSubCaseId(Integer caseSubCaseId);
	public CaseTypeNatureCategoryMap update(CaseTypeNatureCategoryMap item);
}
