package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseTypeNatureCategoryMap;

@Transactional
@Component
public class CaseTypeNatureCategoryMapServiceImp implements CaseTypeNatureCategoryMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeNatureCategoryMap> findAll() {
		Query query = em.createNamedQuery("CaseTypeNatureCategoryMap.findAll");
		 List<CaseTypeNatureCategoryMap> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseTypeNatureCategoryMap create(CaseTypeNatureCategoryMap item) {
		em.persist(item);
		return item;
	}

	@Override
	public CaseTypeNatureCategoryMap findByCaseSubCaseNature(Integer caseSubCaseId,Integer natureId) {
		Query query = em.createNamedQuery("CaseTypeNatureCategoryMap.findByCaseSubCaseNature")
				.setParameter("natureId", natureId)
				.setParameter("caseSubCaseId", caseSubCaseId);
		CaseTypeNatureCategoryMap item = (CaseTypeNatureCategoryMap) query.getSingleResult();

		return item;
	}
	
	@Override
	public CaseTypeNatureCategoryMap findByCaseSubCaseCategory(Integer caseSubCaseId,Integer caseCategoryId) {
		Query query = em.createNamedQuery("CaseTypeNatureCategoryMap.findByCaseSubCaseCategory")
				.setParameter("caseCategoryId", caseCategoryId)
				.setParameter("caseSubCaseId", caseSubCaseId);
		CaseTypeNatureCategoryMap item = (CaseTypeNatureCategoryMap) query.getSingleResult();

		return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeNatureCategoryMap> findByCaseSubCaseId(Integer caseSubCaseId) {
		Query query = em.createNamedQuery("CaseTypeNatureCategoryMap.findByCaseSubCaseId")
				.setParameter("caseSubCaseId", caseSubCaseId);
		List<CaseTypeNatureCategoryMap> items = query.getResultList();

		return items;
	}

	@Override
	@Transactional
	public CaseTypeNatureCategoryMap update(CaseTypeNatureCategoryMap item) {
		em.merge(item);
		return item;
	}

}
