package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.StatusBusinessRule;

public interface StatusBusinessRuleService {
	public List<StatusBusinessRule> findAll();
	public StatusBusinessRule update(StatusBusinessRule item);
	public StatusBusinessRule create(StatusBusinessRule item);
	public StatusBusinessRule findById(Integer id);
	public void deleteByObject(StatusBusinessRule item);
	public void deleteById(Integer id);
}
