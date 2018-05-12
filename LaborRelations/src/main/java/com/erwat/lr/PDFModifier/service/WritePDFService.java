package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.WritePDF;

public interface WritePDFService {
	
	public List<WritePDF> findAll();
	public WritePDF update(WritePDF pdf);
	public WritePDF create(WritePDF pdf);
	public WritePDF findById(Long id);
	public WritePDF findByPageNoOfDoc(Integer pageNo, String docName);
	public List<WritePDF> findByDocName(String docName);
	public void deleteById(Long id);
	public void deleteByObject(WritePDF pdf);
	

}
