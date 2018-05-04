package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseStatus;

@Transactional
@Component
public class CaseStatusServiceImp implements CaseStatusService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseStatus> findAll() {
		Query query = em.createNamedQuery("CaseStatus.findAll");
		 List<CaseStatus> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseStatus update(CaseStatus item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseStatus create(CaseStatus item) {
		em.persist(item);
        return item;
	}

	@Override
	public CaseStatus findById(Integer id) {
		CaseStatus item = em.find(CaseStatus.class, id);
        return item;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseStatus item) {

		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseStatus item = em.find(CaseStatus.class, id);
		em.remove(item);

	}

}
