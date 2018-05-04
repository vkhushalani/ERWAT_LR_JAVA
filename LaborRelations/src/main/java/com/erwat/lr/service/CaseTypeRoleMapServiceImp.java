package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseTypeRoleMap;
@Transactional
@Component
public class CaseTypeRoleMapServiceImp implements CaseTypeRoleMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeRoleMap> findAll() {
		Query query = em.createNamedQuery("CaseTypeRoleMap.findAll");
		 List<CaseTypeRoleMap> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseTypeRoleMap create(CaseTypeRoleMap item) {
		em.persist(item);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeRoleMap> findByRoleId(Integer roleId) {
		Query query = em.createNamedQuery("CaseTypeRoleMap.findByRoleId")
				.setParameter("roleId", roleId);
		List<CaseTypeRoleMap> items = query.getResultList();

  return items;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeRoleMap> findByCaseSubCaseId(Integer caseSubCaseId) {
		Query query = em.createNamedQuery("CaseTypeRoleMap.findByCaseSubCaseId")
				.setParameter("caseSubCaseId", caseSubCaseId);
		List<CaseTypeRoleMap> items = query.getResultList();

  return items;
	}

	@Override
	@Transactional
	public CaseTypeRoleMap update(CaseTypeRoleMap item) {
		em.merge(item);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeRoleMap> findByStageCaseSubCaseId(Integer caseSubCaseId, Integer stage) {
		Query query = em.createNamedQuery("CaseTypeRoleMap.findByStageCaseSubCaseId")
				.setParameter("caseSubCaseId", caseSubCaseId)
				.setParameter("stage",stage);
		List<CaseTypeRoleMap> items = query.getResultList();
		return items;
	}

	@Override
	public CaseTypeRoleMap findById(Integer caseSubCaseId, Integer stage, Integer roleId) {
		Query query = em.createNamedQuery("CaseTypeRoleMap.findById")
				.setParameter("caseSubCaseId", caseSubCaseId)
				.setParameter("stage",stage)
				.setParameter("roleId", roleId);
		CaseTypeRoleMap item = (CaseTypeRoleMap) query.getSingleResult();
		return item;
	}

}
