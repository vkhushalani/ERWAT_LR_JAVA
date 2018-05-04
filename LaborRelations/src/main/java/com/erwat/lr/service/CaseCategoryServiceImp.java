package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseCategory;

@Transactional
@Component
public class CaseCategoryServiceImp implements CaseCategoryService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseCategory> findAll() {
		Query query = em.createNamedQuery("CaseCategory.findAll");
		 List<CaseCategory> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseCategory update(CaseCategory item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseCategory create(CaseCategory item) {
		em.persist(item);
       return item;
	}

	@Override
	public CaseCategory findById(Integer id) {
		CaseCategory item = em.find(CaseCategory.class, id);
       return item;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseCategory item) {

		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseCategory item = em.find(CaseCategory.class, id);
		em.remove(item);

	}


}
