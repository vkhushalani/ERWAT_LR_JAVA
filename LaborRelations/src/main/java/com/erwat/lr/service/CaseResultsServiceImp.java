package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseResults;

@Transactional
@Component
public class CaseResultsServiceImp implements CaseResultsService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseResults> findAll() {
		Query query = em.createNamedQuery("CaseResults.findAll");
		 List<CaseResults> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseResults update(CaseResults item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseResults create(CaseResults item) {
		em.persist(item);
	    return item;
	}

	@Override
	public CaseResults findById(Integer id) {
		CaseResults item = em.find(CaseResults.class, id);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseResults> findByLodgedCaseId(Integer lodgedCaseId) {
		Query query = em.createNamedQuery("CaseResults.findByLodgedCaseId")
				.setParameter("lodgedCaseId", lodgedCaseId);
		List<CaseResults> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseResults> findByLodgedCaseStatusId(Integer lodgedCaseId, Integer caseStatusId) {
		Query query = em.createNamedQuery("CaseResults.findByLodgedCaseStatusId")
				.setParameter("lodgedCaseId", lodgedCaseId)
				.setParameter("caseStatusId", caseStatusId);
		List<CaseResults> items = query.getResultList();

    return items;
	}

	@Override
	public void deleteByObject(CaseResults item) {
		em.remove(item);

	}

	@Override
	public void deleteById(Integer id) {
		CaseResults item = em.find(CaseResults.class, id);
		em.remove(item);

	}

	@Override
	public CaseResults findByLodgedCaseAccepted(Integer lodgedCaseId, Boolean accepted,Integer statusId) {
		try{
		Query query = em.createNamedQuery("CaseResults.findByLodgedCaseAccepted")
				.setParameter("lodgedCaseId", lodgedCaseId)
				.setParameter("accepted", accepted)
				.setParameter("statusId", statusId);
		CaseResults item = (CaseResults) query.getSingleResult();
				return item;
		}
		catch (NoResultException e){
			return null;
		}
    
	}

	@Override
	public CaseResults findByLodgedCaseStatusOutcome(Integer lodgedCaseId, Integer outcomeId,Integer statusId) {
		try{
			Query query = em.createNamedQuery("CaseResults.findByLodgedCaseStatusOutcome")
					.setParameter("lodgedCaseId", lodgedCaseId)
					.setParameter("outcomeId", outcomeId)
					.setParameter("statusId", statusId);
			CaseResults item = (CaseResults) query.getSingleResult();
					return item;
			}
			catch (NoResultException e){
				return null;
			}
	}

}
