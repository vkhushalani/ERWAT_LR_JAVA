package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFLabelsMap;

public interface PDFLabelsMapService {
	
	public List<PDFLabelsMap> findAll();
	public PDFLabelsMap create(PDFLabelsMap item);
	public PDFLabelsMap update(PDFLabelsMap item);
	public PDFLabelsMap findById(Integer pdfID,String valueID);
	public List<PDFLabelsMap> findByLabelId(String labelID);
	public List<PDFLabelsMap> findByPDFId(Integer pdfID);
	public void deleteById(Integer pdfID,String valueID);


}
