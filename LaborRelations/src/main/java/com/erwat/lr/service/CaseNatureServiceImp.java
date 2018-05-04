package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseNature;

@Transactional
@Component
public class CaseNatureServiceImp implements CaseNatureService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseNature> findAll() {
		Query query = em.createNamedQuery("CaseNature.findAll");
		 List<CaseNature> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseNature update(CaseNature item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseNature create(CaseNature item) {
		em.persist(item);
	       return item;
	}

	@Override
	public CaseNature findById(Integer id) {
		CaseNature item = em.find(CaseNature.class, id);
	       return item;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseNature item) {
		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseNature item = em.find(CaseNature.class, id);
		em.remove(item);

	}

}
