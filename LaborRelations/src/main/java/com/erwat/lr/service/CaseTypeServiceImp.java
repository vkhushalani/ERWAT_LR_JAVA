package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseTypes;

@Transactional
@Component
public class CaseTypeServiceImp implements CaseTypeService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseTypes> findAll() {
		Query query = em.createNamedQuery("CaseTypes.findAll");
		 List<CaseTypes> caseTypes = query.getResultList();

	        return caseTypes;
	}

	@Override
	@Transactional
	public CaseTypes update(CaseTypes caseType) {
		em.merge(caseType);
		return caseType;
	}

	@Override
	@Transactional
	public CaseTypes create(CaseTypes caseType) {
		em.persist(caseType);
        return caseType;
	}

	@Override
	public CaseTypes findById(Integer caseId) {
		CaseTypes caseType = em.find(CaseTypes.class, caseId);

	        return caseType;
	}
	
	@Override
	@Transactional
	public void deleteById(Integer caseId) {
		
		CaseTypes caseType = em.find(CaseTypes.class, caseId);
		em.remove(caseType);

	}

	@Override
	public void deleteByObject(CaseTypes caseType) {
		em.remove(caseType);

	}
	

}
