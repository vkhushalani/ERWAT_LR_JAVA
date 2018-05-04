package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseResults;

public interface CaseResultsService {
	public List<CaseResults> findAll();
	public CaseResults update(CaseResults item);
	public CaseResults create(CaseResults item);
	public CaseResults findById(Integer id);
	public List<CaseResults> findByLodgedCaseId(Integer lodgedCaseId);
	public List<CaseResults> findByLodgedCaseStatusId(Integer lodgedCaseId,Integer caseStatusId);
	public CaseResults findByLodgedCaseAccepted(Integer lodgedCaseId, Boolean accepted,Integer statusId);
	public CaseResults findByLodgedCaseStatusOutcome(Integer lodgedCaseId, Integer outcomeId,Integer statusId);
	public void deleteByObject(CaseResults item);
	public void deleteById(Integer id);
}
