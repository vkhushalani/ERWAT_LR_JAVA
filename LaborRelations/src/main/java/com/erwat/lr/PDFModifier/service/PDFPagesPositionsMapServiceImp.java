package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFPagesPositionsMap;
@Transactional
@Component
public class PDFPagesPositionsMapServiceImp implements PDFPagesPositionsMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPagesPositionsMap> findAll() {
		
		Query query = em.createNamedQuery("PDFPagesPositionsMap.findAll");
		 List<PDFPagesPositionsMap> items = query.getResultList();

	        return items;
	}

	@Override
	public PDFPagesPositionsMap findById(Integer pageId, Integer posId) {
		Query query = em.createNamedQuery("PDFPagesPositionsMap.findById")
				.setParameter("pageId", pageId)
				.setParameter("posId", posId);
		 PDFPagesPositionsMap item = (PDFPagesPositionsMap) query.getSingleResult();

	        return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPagesPositionsMap> findByPageId(Integer pageId) {
		Query query = em.createNamedQuery("PDFPagesPositionsMap.findByPageId")
				.setParameter("pageId", pageId);
		 List<PDFPagesPositionsMap> items = query.getResultList();

	        return items;
	}

}
