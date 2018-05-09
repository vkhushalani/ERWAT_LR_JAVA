package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFValues;

public interface PDFValuesService {
	public List<PDFValues> findAll();
	public PDFValues update(PDFValues pdfvalues);
	public PDFValues create(PDFValues pdfvalues);
	public PDFValues findById(String id);;
	public void deleteById(String id);
	public void deleteByObject(PDFValues pdfvalues);
	
}
