package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseNatureCategoryMap;

public interface CaseNatureCategoryMapService {
	public List<CaseNatureCategoryMap> findAll();
	public CaseNatureCategoryMap create(CaseNatureCategoryMap item);
	public  List<CaseNatureCategoryMap> findByCaseCategoryId(Integer caseCategoryId);
	public  List<CaseNatureCategoryMap> findByCaseNatureId(Integer natureId);
	public  CaseNatureCategoryMap findById(Integer caseCategoryId,Integer natureId);
	public CaseNatureCategoryMap update(CaseNatureCategoryMap item);


}
