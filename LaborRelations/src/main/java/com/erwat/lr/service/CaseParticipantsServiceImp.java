package com.erwat.lr.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.model.CaseParticipants;

@Transactional
@Component
public class CaseParticipantsServiceImp implements CaseParticipantsService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipants> findAll() {
		Query query = em.createNamedQuery("CaseParticipants.findAll");
		 List<CaseParticipants> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CaseParticipants update(CaseParticipants item) {
		em.merge(item);
		return item;
	}

	@Override
	@Transactional
	public CaseParticipants create(CaseParticipants item) {
		em.persist(item);
	    return item;
	}

	@Override
	public CaseParticipants findById(Integer id) {
		CaseParticipants item = em.find(CaseParticipants.class, id);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipants> findByLodgedCaseId(Integer lodgedCaseId) {
		Query query = em.createNamedQuery("CaseParticipants.findByLodgedCaseId")
				.setParameter("lodgedCaseId", lodgedCaseId);
		List<CaseParticipants> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public void deleteByObject(CaseParticipants item) {
		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		CaseParticipants item = em.find(CaseParticipants.class, id);
		em.remove(item);

	}

	@Override
	public CaseParticipants findByLodgedCaseRoleStage(Integer lodgedCaseId, Integer roleId,Integer stage) {
		try{
		Query query = em.createNamedQuery("CaseParticipants.findByLodgedCaseRoleStage")
				.setParameter("lodgedCaseId", lodgedCaseId)
				.setParameter("roleId", roleId)
				.setParameter("stage", stage);
		 CaseParticipants item = (CaseParticipants) query.getSingleResult();

	        return item;
		}
		catch (NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipants> findByParticipantId(String participantId) {
		Query query = em.createNamedQuery("CaseParticipants.findByParticipantId")
				.setParameter("participantId", participantId);
		List<CaseParticipants> items = query.getResultList();
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipants> findByLodgedCaseStage(Integer lodgedCaseId, Integer stage) {
		Query query = em.createNamedQuery("CaseParticipants.findByLodgedCaseStage")
				.setParameter("lodgedCaseId", lodgedCaseId)
				.setParameter("stage", stage);
		List<CaseParticipants> items = query.getResultList();
		return items;
	}

}
