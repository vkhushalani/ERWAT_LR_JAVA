package com.erwat.lr.PDFModifier.service;

import java.util.List;

import com.erwat.lr.PDFModifier.model.PDFPagesPositionsMap;

public interface PDFPagesPositionsMapService {
	public List<PDFPagesPositionsMap> findAll();
	public PDFPagesPositionsMap findById(Integer pageId, Integer posId);
	public List<PDFPagesPositionsMap> findByPageId(Integer pageId);

}
