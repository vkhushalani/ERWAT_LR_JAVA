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
	@Transactional
	public PDFValues update(PDFValues pdfvalues) {
		em.merge(pdfvalues);
		 return pdfvalues;
	}

	@Override
	@Transactional
	public PDFValues create(PDFValues pdfvalues) {
		em.persist(pdfvalues);
		 return pdfvalues;
	}

	@Override
	public PDFValues findById(String id) {
		PDFValues item = em.find(PDFValues.class, id);
        return item;
	}

	@Override
	public void deleteById(String id) {
		PDFValues item = em.find(PDFValues.class, id);
		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteByObject(PDFValues pdfvalues) {
		em.remove(pdfvalues);

	}

}
