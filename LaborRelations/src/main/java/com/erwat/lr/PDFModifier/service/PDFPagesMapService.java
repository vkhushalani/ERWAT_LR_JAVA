package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFPagesMap;

public interface PDFPagesMapService {
	
	public List<PDFPagesMap> findAll();
	public PDFPagesMap findById(Integer pdfId,Integer pageId);
	public List<PDFPagesMap> findByPdfId(Integer pdfId);
	public PDFPagesMap findByPageNoId(Integer pdfId, Integer pageNo);

}

