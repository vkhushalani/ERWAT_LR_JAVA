package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFValues;

public interface PDFValuesService {
	
	public List<PDFValues> findAll();
	public PDFValues findById(Integer id);
	public List<PDFValues> findByValue(String value);
	

}
