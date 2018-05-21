package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFPages;

@Transactional
@Component
public class PDFPagesServiceImp implements PDFPagesService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPages> findAll() {
		Query query = em.createNamedQuery("PDFPages.findAll");
		 List<PDFPages> items = query.getResultList();

	        return items;
	}

	@Override
	public PDFPages findById(Integer id) {
		PDFPages item = em.find(PDFPages.class, id);
        return item;
	}

}
