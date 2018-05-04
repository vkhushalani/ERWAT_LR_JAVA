package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseStatus;

public interface CaseStatusService {
	
	public List<CaseStatus> findAll();
	public CaseStatus update(CaseStatus item);
	public CaseStatus create(CaseStatus item);
	public CaseStatus findById(Integer id);
	public void deleteByObject(CaseStatus item);
	public void deleteById(Integer id);

}
