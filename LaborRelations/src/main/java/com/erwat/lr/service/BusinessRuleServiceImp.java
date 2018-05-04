package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.BusinessRule;

@Transactional
@Component
public class BusinessRuleServiceImp implements BusinessRuleService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessRule> findAll() {
		Query query = em.createNamedQuery("BusinessRule.findAll");
		 List<BusinessRule> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public BusinessRule update(BusinessRule item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public BusinessRule create(BusinessRule item) {
		em.persist(item);
	       return item;
	}

	@Override
	public BusinessRule findById(Integer id) {
		BusinessRule item = em.find(BusinessRule.class, id);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessRule> findByCaseId(Integer caseId) {
		Query query = em.createNamedQuery("BusinessRule.findByCaseId")
						.setParameter("caseId", caseId);
		 List<BusinessRule> items = query.getResultList();

	        return items;
	}

	@Override
	public void deleteByObject(BusinessRule item) {
		em.remove(item);

	}

	@Override
	public void deleteById(Integer id) {
		BusinessRule item = em.find(BusinessRule.class, id);
		em.remove(item);
	}

}
