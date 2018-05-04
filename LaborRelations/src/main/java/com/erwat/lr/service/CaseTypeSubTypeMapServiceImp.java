package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseTypeSubTypeMap;

@Transactional
@Component
public class CaseTypeSubTypeMapServiceImp implements CaseTypeSubTypeMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeSubTypeMap> findAll() {
		Query query = em.createNamedQuery("CaseTypeSubTypeMap.findAll");
		 List<CaseTypeSubTypeMap> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseTypeSubTypeMap create(CaseTypeSubTypeMap item) {
		em.persist(item);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeSubTypeMap> findByCaseId(Integer caseId) {
		Query query = em.createNamedQuery("CaseTypeSubTypeMap.findByCaseId")
						.setParameter("caseId", caseId);
		 List<CaseTypeSubTypeMap> items = query.getResultList();

	        return items;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeSubTypeMap> findBySubCaseId(Integer subCaseId) {
		Query query = em.createNamedQuery("CaseTypeSubTypeMap.findBySubCaseId")
				.setParameter("subCaseId", subCaseId);
 List<CaseTypeSubTypeMap> items = query.getResultList();

    return items;
	}

	@Override
	public CaseTypeSubTypeMap findById(Integer id) {
		CaseTypeSubTypeMap item = em.find(CaseTypeSubTypeMap.class, id);
		return item;
	}

	@Override
	public CaseTypeSubTypeMap findByCaseSubCaseId(Integer caseId, Integer subCaseId) {
		Query query = em.createNamedQuery("CaseTypeSubTypeMap.findByCaseSubCaseId")
				.setParameter("subCaseId", subCaseId)
				.setParameter("caseId", caseId);
		CaseTypeSubTypeMap item = (CaseTypeSubTypeMap) query.getSingleResult();
		return item;
	}

}
