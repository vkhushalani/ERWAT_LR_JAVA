package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFPositionValueMap;

public interface PDFPositionValueMapService {
	
	public List<PDFPositionValueMap> findAll();
	public PDFPositionValueMap findById(Integer posId,Integer valueId);
	public List<PDFPositionValueMap> findByPosId(Integer posId);


}
