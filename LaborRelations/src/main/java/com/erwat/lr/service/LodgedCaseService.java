package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.LodgedCase;

public interface LodgedCaseService {
	
	public List<LodgedCase> findAll();
	public LodgedCase update(LodgedCase item);
	public LodgedCase create(LodgedCase item);
	public LodgedCase findById(Integer id);
	public List<LodgedCase> findByParentCaseId(Integer pCaseId);
	public List<LodgedCase> findByEmployeeId(String employeeId);
	public List<LodgedCase> findByNatureId(Integer natureId);
	public List<LodgedCase> findByCaseSubCaseId(Integer caseSubCaseId);
	public LodgedCase findByCaseNumber(String caseNumber);
	public void deleteByObject(LodgedCase item);
	public void deleteById(Integer id);

}
