package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFPages;

public interface PDFPagesService {
	
	public List<PDFPages> findAll();
	public PDFPages findById(Integer id);

}
