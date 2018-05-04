package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseNatureCategoryMap;

@Transactional
@Component
public class CaseNatureCategoryMapServiceImp implements CaseNatureCategoryMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseNatureCategoryMap> findAll() {
		Query query = em.createNamedQuery("CaseNatureCategoryMap.findAll");
		 List<CaseNatureCategoryMap> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseNatureCategoryMap create(CaseNatureCategoryMap item) {
		em.persist(item);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseNatureCategoryMap> findByCaseCategoryId(Integer caseCategoryId) {
		Query query = em.createNamedQuery("CaseNatureCategoryMap.findByCaseCategoryId")
				.setParameter("caseCategoryId", caseCategoryId);
		List<CaseNatureCategoryMap> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseNatureCategoryMap> findByCaseNatureId(Integer natureId) {
		Query query = em.createNamedQuery("CaseNatureCategoryMap.findByCaseNatureId")
				.setParameter("natureId", natureId);
		List<CaseNatureCategoryMap> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public CaseNatureCategoryMap update(CaseNatureCategoryMap item) {
		em.merge(item);
		return item;
	}

	@Override
	public CaseNatureCategoryMap findById(Integer caseCategoryId, Integer natureId) {
		Query query = em.createNamedQuery("CaseNatureCategoryMap.findByCaseCategoryId")
				.setParameter("caseCategoryId", caseCategoryId)
				.setParameter("natureId", natureId);
		CaseNatureCategoryMap item = (CaseNatureCategoryMap) query.getSingleResult();

    return item;
	}

}
