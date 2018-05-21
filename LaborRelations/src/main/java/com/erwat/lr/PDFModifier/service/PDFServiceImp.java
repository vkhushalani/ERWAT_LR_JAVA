package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDF;


@Transactional
@Component
public class PDFServiceImp implements PDFService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDF> findAll() {
		Query query = em.createNamedQuery("PDF.findAll");
		 List<PDF> items = query.getResultList();

	        return items;
	}


	@Override
	public PDF findById(Integer id) {
		PDF item = em.find(PDF.class, id);
        return item;
	}

	@Override
	public PDF findByDocName(String docName) {
		Query query = em.createNamedQuery("PDF.findByDocName")
				.setParameter("docName", docName);
		PDF item = (PDF) query.getSingleResult();

		return item;
	}

}
