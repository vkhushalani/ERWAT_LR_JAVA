package com.erwat.lr.PDFModifier.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.PDFModifier.model.PDFValues;
import com.erwat.lr.PDFModifier.service.PDFValuesService;

@RestController
@RequestMapping("/PDFBuilder")
public class PDFValuesController {
	
	Logger logger = LoggerFactory.getLogger(PDFValuesController.class);
	@Autowired
	PDFValuesService pdfValuesService;
	
	@GetMapping("/DefaultValues")
	public ResponseEntity<List<PDFValues>> getAllDefaultValues() {
		
		List<PDFValues> defaultPdfValues =  pdfValuesService.findAll();
		return ResponseEntity.ok().body(defaultPdfValues);
		
	}
}
