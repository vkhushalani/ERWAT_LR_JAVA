package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.LodgedCase;

@Transactional
@Component
public class LodgedCaseServiceImp implements LodgedCaseService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LodgedCase> findAll() {
		Query query = em.createNamedQuery("LodgedCase.findAll");
		 List<LodgedCase> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public LodgedCase update(LodgedCase item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public LodgedCase create(LodgedCase item) {
		em.persist(item);
	    return item;
	}

	@Override
	public LodgedCase findById(Integer id) {
		LodgedCase item = em.find(LodgedCase.class, id);
		return item;
	}

	@Override
	public LodgedCase findByCaseNumber(String caseNumber) {
		Query query = em.createNamedQuery("LodgedCase.findByCaseNumber")
				.setParameter("caseNumber", caseNumber);
		LodgedCase item = (LodgedCase) query.getSingleResult();

    return item;
	}

	@Override
	@Transactional
	public void deleteByObject(LodgedCase item) {
		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		LodgedCase item = em.find(LodgedCase.class, id);
		em.remove(item);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LodgedCase> findByParentCaseId(Integer pCaseId) {
		Query query = em.createNamedQuery("LodgedCase.findByParentCaseId")
				.setParameter("parentCaseId", pCaseId);
		List<LodgedCase> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LodgedCase> findByEmployeeId(String employeeId) {
		Query query = em.createNamedQuery("LodgedCase.findByEmployeeId")
				.setParameter("employeeId", employeeId);
		List<LodgedCase> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LodgedCase> findByNatureId(Integer natureId) {
		Query query = em.createNamedQuery("LodgedCase.findByNatureId")
				.setParameter("natureId", natureId);
		List<LodgedCase> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LodgedCase> findByCaseSubCaseId(Integer caseSubCaseId) {
		Query query = em.createNamedQuery("LodgedCase.findByCaseSubCaseId")
				.setParameter("caseSubCaseId", caseSubCaseId);
		List<LodgedCase> items = query.getResultList();

    return items;
	}

}
