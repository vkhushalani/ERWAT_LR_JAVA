package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseOutcome;

@Transactional
@Component
public class CaseOutcomeServiceImp implements CaseOutcomeService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseOutcome> findAll() {
		Query query = em.createNamedQuery("CaseOutcome.findAll");
		 List<CaseOutcome> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseOutcome update(CaseOutcome item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseOutcome create(CaseOutcome item) {
		em.persist(item);
       return item;
	}

	@Override
	public CaseOutcome findById(Integer id) {
		CaseOutcome item = em.find(CaseOutcome.class, id);
       return item;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseOutcome item) {

		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseOutcome item = em.find(CaseOutcome.class, id);
		em.remove(item);

	}
}
