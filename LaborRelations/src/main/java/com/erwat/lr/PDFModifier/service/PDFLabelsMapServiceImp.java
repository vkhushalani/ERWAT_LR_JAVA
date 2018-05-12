package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFLabelsMap;


@Transactional
@Component
public class PDFLabelsMapServiceImp implements PDFLabelsMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFLabelsMap> findAll() {
		Query query = em.createNamedQuery("PDFLabelsMap.findAll");
		 List<PDFLabelsMap> items = query.getResultList();
	        return items;
	
	}

	@Override
	@Transactional
	public PDFLabelsMap create(PDFLabelsMap item) {
		 em.persist(item);
//       em.getTransaction().commit();
       return item;
	}

	@Override
	@Transactional
	public PDFLabelsMap update(PDFLabelsMap item) {
		  em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public PDFLabelsMap findById(Integer pdfID, String labelID) {
		Query query = em.createNamedQuery("PDFLabelsMap.findById")
				.setParameter("pdfID",pdfID)
				.setParameter("labelID",labelID);
		PDFLabelsMap item = (PDFLabelsMap) query.getSingleResult();

		return item;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PDFLabelsMap> findByLabelId(String labelID) {
		Query query = em.createNamedQuery("PDFLabelsMap.findByLabelId")
				.setParameter("labelID",labelID);
		List<PDFLabelsMap> items = query.getResultList();
		return items;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PDFLabelsMap> findByPDFId(Integer pdfID) {
		Query query = em.createNamedQuery("PDFLabelsMap.findByPDFId")
				.setParameter("pdfID",pdfID);
		List<PDFLabelsMap> items = query.getResultList();
		return items;
	}

	@Override
	@Transactional
	public void deleteById(Integer pdfID, String valueID) {
		PDFLabelsMap item = findById(pdfID,valueID);
		em.remove(item);

	}



}
