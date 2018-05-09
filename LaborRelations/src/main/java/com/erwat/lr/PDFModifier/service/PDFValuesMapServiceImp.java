package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFValuesMap;


@Transactional
@Component
public class PDFValuesMapServiceImp implements PDFValuesMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFValuesMap> findAll() {
		Query query = em.createNamedQuery("PDFValuesMap.findAll");
		 List<PDFValuesMap> items = query.getResultList();
	        return items;
	
	}

	@Override
	@Transactional
	public PDFValuesMap create(PDFValuesMap item) {
		 em.persist(item);
//       em.getTransaction().commit();
       return item;
	}

	@Override
	@Transactional
	public PDFValuesMap update(PDFValuesMap item) {
		  em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public PDFValuesMap findById(Integer pdfID, String valueID) {
		Query query = em.createNamedQuery("PDFValuesMap.findById")
				.setParameter("pdfID",pdfID)
				.setParameter("valueID",valueID);
		PDFValuesMap item = (PDFValuesMap) query.getSingleResult();

		return item;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PDFValuesMap> findByValueId(String valueID) {
		Query query = em.createNamedQuery("PDFValuesMap.findByValueId")
				.setParameter("valueID",valueID);
		List<PDFValuesMap> items = query.getResultList();
		return items;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PDFValuesMap> findByPDFId(Integer pdfID) {
		Query query = em.createNamedQuery("PDFValuesMap.findByPDFId")
				.setParameter("pdfID",pdfID);
		List<PDFValuesMap> items = query.getResultList();
		return items;
	}

	@Override
	@Transactional
	public void deleteById(Integer pdfID, String valueID) {
		PDFValuesMap item = findById(pdfID,valueID);
		em.remove(item);

	}



}
