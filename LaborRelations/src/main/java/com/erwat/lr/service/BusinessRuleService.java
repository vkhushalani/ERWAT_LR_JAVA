package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.BusinessRule;

public interface BusinessRuleService {
	public List<BusinessRule> findAll();
	public BusinessRule update(BusinessRule item);
	public BusinessRule create(BusinessRule item);
	public BusinessRule findById(Integer id);
	public List<BusinessRule> findByCaseId(Integer caseId);
	public void deleteByObject(BusinessRule item);
	public void deleteById(Integer id);
}
