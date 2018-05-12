package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFLabels;

public interface PDFLabelsService {
	public List<PDFLabels> findAll();
	public PDFLabels update(PDFLabels pdfLabels);
	public PDFLabels create(PDFLabels pdfLabels);
	public PDFLabels findById(String id);;
	public void deleteById(String id);
	public void deleteByObject(PDFLabels pdfLabels);
	
}
