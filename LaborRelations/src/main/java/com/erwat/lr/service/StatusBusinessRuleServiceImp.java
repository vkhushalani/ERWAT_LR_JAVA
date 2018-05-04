package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.StatusBusinessRule;

@Transactional
@Component
public class StatusBusinessRuleServiceImp implements StatusBusinessRuleService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StatusBusinessRule> findAll() {
		Query query = em.createNamedQuery("StatusBusinessRule.findAll");
		 List<StatusBusinessRule> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public StatusBusinessRule update(StatusBusinessRule item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public StatusBusinessRule create(StatusBusinessRule item) {
		em.persist(item);
	       return item;
	}

	@Override
	public StatusBusinessRule findById(Integer id) {
		StatusBusinessRule item = em.find(StatusBusinessRule.class, id);
		return item;
	}

	@Override
	public void deleteByObject(StatusBusinessRule item) {
		em.remove(item);

	}

	@Override
	public void deleteById(Integer id) {
		StatusBusinessRule item = em.find(StatusBusinessRule.class, id);
		em.remove(item);
	}

}
