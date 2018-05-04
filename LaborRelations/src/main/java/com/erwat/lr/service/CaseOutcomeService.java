package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseOutcome;

public interface CaseOutcomeService {

	public List<CaseOutcome> findAll();
	public CaseOutcome update(CaseOutcome item);
	public CaseOutcome create(CaseOutcome item);
	public CaseOutcome findById(Integer id);
	public void deleteByObject(CaseOutcome item);
	public void deleteById(Integer id);
}
