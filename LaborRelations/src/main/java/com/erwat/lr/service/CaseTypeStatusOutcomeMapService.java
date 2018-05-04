package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseTypeStatusOutcomeMap;

public interface CaseTypeStatusOutcomeMapService {
	public List<CaseTypeStatusOutcomeMap> findAll();
	public CaseTypeStatusOutcomeMap create(CaseTypeStatusOutcomeMap item);
	public  CaseTypeStatusOutcomeMap findById(Integer caseSubCaseId ,Integer statusId,Integer outcomeId);
	public  List<CaseTypeStatusOutcomeMap> findByOutcomeId(Integer outcomeId);
	public  List<CaseTypeStatusOutcomeMap> findByCaseSubCaseId(Integer caseSubCaseId);
	public  List<CaseTypeStatusOutcomeMap> findByStatusId(Integer statusId);
	public  List<CaseTypeStatusOutcomeMap> findStatusBySequence(Integer caseSubCaseId, Integer sequence);
	public List<CaseTypeStatusOutcomeMap> findByCaseSatusPerCaseSubCase(Integer caseSubCaseId ,Integer statusId);
	public CaseTypeStatusOutcomeMap update(CaseTypeStatusOutcomeMap item);

}
