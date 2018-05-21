package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFPositions;

public interface PDFPositionsService {
	
	public List<PDFPositions> findAll();
	public PDFPositions findById(Integer id);
	public PDFPositions findByPosName (String posName);
	
}
