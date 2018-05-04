package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseRole;

public interface CaseRoleService {
	public List<CaseRole> findAll();
	public CaseRole update(CaseRole item);
	public CaseRole create(CaseRole item);
	public CaseRole findById(Integer id);
	public void deleteByObject(CaseRole item);
	public void deleteById(Integer id);
}
