package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseTypeStatusOutcomeMap;

@Transactional
@Component
public class CaseTypeStatusOutcomeMapServiceImp implements CaseTypeStatusOutcomeMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findAll() {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findAll");
		 List<CaseTypeStatusOutcomeMap> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseTypeStatusOutcomeMap create(CaseTypeStatusOutcomeMap item) {
		em.persist(item);
		return item;
	}

	@Override
	public CaseTypeStatusOutcomeMap findById(Integer caseSubCaseId, Integer statusId, Integer outcomeId) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findById")
						.setParameter("caseStatusId", statusId)
						.setParameter("caseSubCaseId", caseSubCaseId)
						.setParameter("outcomeId", outcomeId);
		 CaseTypeStatusOutcomeMap item = (CaseTypeStatusOutcomeMap) query.getSingleResult();
	        return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findByOutcomeId(Integer outcomeId) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findByOutcomeId")
				.setParameter("outcomeId", outcomeId);
		List<CaseTypeStatusOutcomeMap> items = query.getResultList();
		return items;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findByCaseSubCaseId(Integer caseSubCaseId) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findByCaseSubCaseId")
				.setParameter("caseSubCaseId", caseSubCaseId);
		List<CaseTypeStatusOutcomeMap> items = query.getResultList();
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findByStatusId(Integer statusId) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findByCaseStatusId")
				.setParameter("caseStatusId", statusId);
		List<CaseTypeStatusOutcomeMap> items = query.getResultList();
		return items;
	}

	@Override
	@Transactional
	public CaseTypeStatusOutcomeMap update(CaseTypeStatusOutcomeMap item) {
		em.merge(item);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findStatusBySequence(Integer caseSubCaseId, Integer sequence) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findStatusBySequence")
				.setParameter("sequence", sequence)
				.setParameter("caseSubCaseId", caseSubCaseId);
		List<CaseTypeStatusOutcomeMap> items = query.getResultList();
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypeStatusOutcomeMap> findByCaseSatusPerCaseSubCase(Integer caseSubCaseId, Integer statusId) {
		Query query = em.createNamedQuery("CaseTypeStatusOutcomeMap.findByCaseSatusPerCaseSubCase")
				.setParameter("caseStatusId", statusId)
				.setParameter("caseSubCaseId", caseSubCaseId);
				List<CaseTypeStatusOutcomeMap> items =  query.getResultList();
		return items;
	}

}
