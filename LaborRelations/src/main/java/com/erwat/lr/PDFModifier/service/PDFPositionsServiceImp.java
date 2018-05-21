package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFPositions;

@Transactional
@Component
public class PDFPositionsServiceImp implements PDFPositionsService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPositions> findAll() {
		Query query = em.createNamedQuery("PDFPositions.findAll");
		 List<PDFPositions> items = query.getResultList();
	        return items;
	}

	@Override
	public PDFPositions findById(Integer id) {
		PDFPositions item = em.find(PDFPositions.class, id);
        return item;
	}
	

	@Override
	public PDFPositions findByPosName(String posName) {
		Query query = em.createNamedQuery("PDFPositions.findByPosName")
				.setParameter("posName", posName);
		 PDFPositions item = (PDFPositions) query.getSingleResult();
	        return item;
	}


}
