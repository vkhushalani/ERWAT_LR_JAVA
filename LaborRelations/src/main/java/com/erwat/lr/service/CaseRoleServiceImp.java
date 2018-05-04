package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseRole;

@Transactional
@Component
public class CaseRoleServiceImp implements CaseRoleService{
	
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseRole> findAll() {
		Query query = em.createNamedQuery("CaseRole.findAll");
		 List<CaseRole> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseRole update(CaseRole item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseRole create(CaseRole item) {
		em.persist(item);
       return item;
	}

	@Override
	public CaseRole findById(Integer id) {
		CaseRole item = em.find(CaseRole.class, id);
       return item;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseRole item) {

		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseRole item = em.find(CaseRole.class, id);
		em.remove(item);

	}

}
