package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFPositionValueMap;


@Transactional
@Component
public class PDFPositionValueMapServiceImp implements PDFPositionValueMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPositionValueMap> findAll() {
		Query query = em.createNamedQuery("PDFPositionValueMap.findAll");
		 List<PDFPositionValueMap> items = query.getResultList();
	        return items;
	
	}



	@Override
	public PDFPositionValueMap findById( Integer posId,Integer valueId) {
		Query query = em.createNamedQuery("PDFPositionValueMap.findById")
				.setParameter("posID",posId)
				.setParameter("valueId",valueId);
		PDFPositionValueMap item = (PDFPositionValueMap) query.getSingleResult();

		return item;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PDFPositionValueMap> findByPosId(Integer posId) {
		Query query = em.createNamedQuery("PDFPositionValueMap.findByPosId")
				.setParameter("posId",posId);
		List<PDFPositionValueMap> items = query.getResultList();
		return items;
	}

	





}
