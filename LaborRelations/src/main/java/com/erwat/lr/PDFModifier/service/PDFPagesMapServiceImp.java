package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.PDFPagesMap;

@Transactional
@Component
public class PDFPagesMapServiceImp implements PDFPagesMapService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPagesMap> findAll() {
		Query query = em.createNamedQuery("PDFPagesMap.findAll");
		 List<PDFPagesMap> items = query.getResultList();

	        return items;
	}

	@Override
	public PDFPagesMap findById(Integer pdfId, Integer pageId) {
		Query query = em.createNamedQuery("PDFPagesMap.findById")
				.setParameter("pdfId", pdfId)
				.setParameter("pageId", pageId);
		PDFPagesMap item = (PDFPagesMap) query.getSingleResult();

		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PDFPagesMap> findByPdfId(Integer pdfId) {
		Query query = em.createNamedQuery("PDFPagesMap.findByPdfId")
				.setParameter("pdfId", pdfId);
		 List<PDFPagesMap> items = query.getResultList();

	        return items;
	}

	@Override
	public PDFPagesMap findByPageNoId(Integer pdfId, Integer pageNo) {
		try{
		Query query = em.createNamedQuery("PDFPagesMap.findByPageNoId")
				.setParameter("pdfId", pdfId)
				.setParameter("pageNo", pageNo);
		PDFPagesMap item = (PDFPagesMap) query.getSingleResult();
		return item;
		}
		catch (NoResultException e)
		{
			return null;
		}
		
	}

}
