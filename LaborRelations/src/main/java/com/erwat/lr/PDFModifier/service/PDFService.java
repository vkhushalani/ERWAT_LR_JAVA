package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDF;

public interface PDFService {
	
	public List<PDF> findAll();
	public PDF findById(Integer id);
	public PDF findByDocName(String docName);

}
