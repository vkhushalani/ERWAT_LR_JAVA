package com.erwat.lr.PDFModifier.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erwat.lr.PDFModifier.model.WritePDF;


@Transactional
@Component
public class WritePDFServiceImp implements WritePDFService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WritePDF> findAll() {
		Query query = em.createNamedQuery("WritePDF.findAll");
		 List<WritePDF> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public WritePDF update(WritePDF pdf) {
		 em.merge(pdf);
		 return pdf;
	}

	@Override
	@Transactional
	public WritePDF create(WritePDF pdf) {
		em.persist(pdf);
		 return pdf;
	}

	@Override
	public WritePDF findById(Long id) {
		WritePDF item = em.find(WritePDF.class, id);
        return item;
	}


	@Override
	public WritePDF findByPageNoOfDoc(Integer pageNo, String docName) {
		Query query = em.createNamedQuery("WritePDF.findByPageNoOfDoc")
						.setParameter("pageNo", pageNo)
						.setParameter("dname", docName);
		 WritePDF item = (WritePDF) query.getSingleResult();

	        return item;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		WritePDF item = em.find(WritePDF.class, id);
		em.remove(item);
	}

	@Override
	@Transactional
	public void deleteByObject(WritePDF pdf) {
		em.remove(pdf);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<WritePDF> findByDocName(String docName) {
		Query query = em.createNamedQuery("WritePDF.findByDocName")
				.setParameter("dname", docName);
		List<WritePDF> items = query.getResultList();

		return items;
	}

}
