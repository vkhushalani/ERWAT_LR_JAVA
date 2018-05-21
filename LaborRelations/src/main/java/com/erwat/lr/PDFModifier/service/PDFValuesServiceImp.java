package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFValues;

@Transactional
@Component
public class PDFValuesServiceImp implements PDFValuesService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFValues> findAll() {
		Query query = em.createNamedQuery("PDFValues.findAll");
		 List<PDFValues> items = query.getResultList();

	        return items;
	}


	@Override
	public PDFValues findById(Integer id) {
		PDFValues item = em.find(PDFValues.class, id);
        return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFValues> findByValue(String value) {
		Query query = em.createNamedQuery("PDFValues.findByValue")
				.setParameter("value", value);
		 List<PDFValues> items = query.getResultList();

	        return items;
	}

}
