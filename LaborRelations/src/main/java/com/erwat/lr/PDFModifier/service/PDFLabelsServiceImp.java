package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFLabels;

@Transactional
@Component
public class PDFLabelsServiceImp implements PDFLabelsService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFLabels> findAll() {
		Query query = em.createNamedQuery("PDFLabels.findAll");
		 List<PDFLabels> items = query.getResultList();
	        return items;
	}

	@Override
	@Transactional
	public PDFLabels update(PDFLabels pdflabels) {
		em.merge(pdflabels);
		 return pdflabels;
	}

	@Override
	@Transactional
	public PDFLabels create(PDFLabels pdflabels) {
		em.persist(pdflabels);
		 return pdflabels;
	}

	@Override
	public PDFLabels findById(String id) {
		PDFLabels item = em.find(PDFLabels.class, id);
        return item;
	}

	@Override
	public void deleteById(String id) {
		PDFLabels item = em.find(PDFLabels.class, id);
		em.remove(item);

	}

	@Override
	@Transactional
	public void deleteByObject(PDFLabels pdflabels) {
		em.remove(pdflabels);

	}

}
