package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFValuesMap;

public interface PDFValuesMapService {
	
	public List<PDFValuesMap> findAll();
	public PDFValuesMap create(PDFValuesMap item);
	public PDFValuesMap update(PDFValuesMap item);
	public PDFValuesMap findById(Integer pdfID,String valueID);
	public List<PDFValuesMap> findByValueId(String valueID);
	public List<PDFValuesMap> findByPDFId(Integer pdfID);
	public void deleteById(Integer pdfID,String valueID);


}
