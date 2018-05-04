package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.SubCaseType;
@Transactional
@Component
public class SubCaseTypeServiceImp implements SubCaseTypeService {
	
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubCaseType> findAll() {
		Query query = em.createNamedQuery("SubCaseType.findAll");
		 List<SubCaseType> caseTypes = query.getResultList();

	        return caseTypes;
	}

	@Override
	@Transactional
	public SubCaseType update(SubCaseType caseType) {
		em.merge(caseType);
		return caseType;
	}

	@Override
	@Transactional
	public SubCaseType create(SubCaseType caseType) {
		em.persist(caseType);
		return caseType;
	}

	@Override
	public SubCaseType findById(Integer id) {
		SubCaseType caseType = em.find(SubCaseType.class, id);

        return caseType;
	}
	

	@Override
	@Transactional
	public void deleteByObject(SubCaseType caseType) {
		em.remove(caseType);	

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		
		SubCaseType caseType = em.find(SubCaseType.class, id);
		em.remove(caseType);

	}

}
