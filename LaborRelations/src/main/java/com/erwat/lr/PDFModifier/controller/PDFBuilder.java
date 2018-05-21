package com.erwat.lr.PDFModifier.controller;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.PDFModifier.model.PDFPositions;
import com.erwat.lr.PDFModifier.model.PDFPositionValueMap;
import com.erwat.lr.PDFModifier.model.PDF;
import com.erwat.lr.PDFModifier.model.PDFPagesMap;
import com.erwat.lr.PDFModifier.model.PDFPagesPositionsMap;
import com.erwat.lr.PDFModifier.service.PDFValuesService;
import com.erwat.lr.PDFModifier.service.PDFPositionsService;
import com.erwat.lr.PDFModifier.service.PDFPositionValueMapService;
import com.erwat.lr.PDFModifier.service.PDFService;
import com.erwat.lr.PDFModifier.service.PDFPagesService;
import com.erwat.lr.PDFModifier.service.PDFPagesMapService;
import com.erwat.lr.PDFModifier.service.PDFPagesPositionsMapService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/PDFBuilder")
public class PDFBuilder {
	
	Logger logger = LoggerFactory.getLogger(PDFBuilder.class);
	 
	@Autowired
	PDFValuesService pdfValuesService;
	 
	 @Autowired
	 PDFPositionsService pdfPositionsService;
	 
	 @Autowired
	 PDFPositionValueMapService pdfPositionValueMapService;
	
	 @Autowired
	 PDFService pdfService;
	 
	 @Autowired
	 PDFPagesService pdfPagesService;
	 
	 @Autowired
	 PDFPagesMapService pdfPagesMapService;
	 
	 @Autowired
	 PDFPagesPositionsMapService pdfPagesPositionsMapService;
	 
	 
	 @GetMapping("/values/{filename}")
		public ResponseEntity<?> getAllValues(@PathVariable("filename") String fName) {
		 List<PDFPagesPositionsMap> allDocPos  = new ArrayList<PDFPagesPositionsMap>();
		 PDF pdf = pdfService.findByDocName(fName);
		 List<PDFPagesMap> pages = pdfPagesMapService.findByPdfId(pdf.getId());
		 
		 	for(PDFPagesMap page : pages)
		 	{
		 		List<PDFPagesPositionsMap> positions =  pdfPagesPositionsMapService.findByPageId(page.getPageId());
		 		allDocPos.addAll(positions);
		 		
		 	}
		 	JSONObject robj =  new JSONObject();
		 	for(PDFPagesPositionsMap pos :allDocPos)
		 	{
		 		JSONObject vobj = new JSONObject();
		 		PDFPositions posValue = pdfPositionsService.findById(pos.getPosId());
		 		List<PDFPositionValueMap> valueMap = pdfPositionValueMapService.findByPosId(pos.getPosId());
		 		if(pos.getFromSystem() != null){
		 		switch (pos.getFromSystem())
		 		{
		 			case 0 :
		 				vobj.put("defaultFlag", false);
		 				for(PDFPositionValueMap value : valueMap){
		 					if(value.getDefaultFlag())
		 					{
		 						vobj.put("value", pdfValuesService.findById(value.getValueId()).getValue());
		 						
		 					}
		 				
		 				}
		 				break;
		 			case 1:
		 				vobj.put("defaultFlag", true);
		 				break;
		 			default:	
		 				vobj.put("defaultFlag", true);
		 				
		 		}
		 		}
		 		else
		 		{vobj.put("defaultFlag", true);}
		 		
		 		
		 		robj.put(posValue.getPosName(), vobj);
		 		
		 	}
		 	
			return ResponseEntity.ok().body(robj.toString());
			
		}
	@SuppressWarnings("unchecked")
	@PostMapping("/generate/{filename}")
	public ResponseEntity<?> writePDF(@PathVariable("filename") String fName,@RequestBody Map<String, Object> inputObject) throws InvalidPasswordException, IOException, PrinterException{
		
		ByteArrayOutputStream out = null;
		
		float fontSize = 10;
		PDFont font = PDType1Font.TIMES_ROMAN;
		
		InputStream is = getClass().getResourceAsStream("/Files/"+fName+".pdf");
		
		PDDocument document  = PDDocument.load(is);
		
		PDPageTree pages = document.getPages();
		PDPage page;
		PDF pdf = pdfService.findByDocName(fName);
		
		
		ObjectMapper oMapper = new ObjectMapper();
		Integer pagesNo = document.getNumberOfPages();

	
		//loop the pages 
		for (int i=0;i< pagesNo;i++)
		{	
			page = pages.get(i);
			
			PDFPagesMap pageMap = pdfPagesMapService.findByPageNoId(pdf.getId(),i+1);
			if(pageMap != null)
			{
				
				List<PDFPagesPositionsMap> positions = pdfPagesPositionsMapService.findByPageId(pageMap.getPageId());
			
				// loop for all the pdf form labels per page
				for(PDFPagesPositionsMap position :positions){
						String sText = "";
						float xOffset = 0;
						float yOffset = 0;
						PDFPositions pos = pdfPositionsService.findById(position.getPosId());
						List<PDFPositionValueMap> valueMap = pdfPositionValueMapService.findByPosId(position.getPosId());
					
						if(position.getFromSystem() != null){
					 		switch (position.getFromSystem())
					 		{
					 			case 0 : 
					 				Object valueObject = inputObject.get(pos.getPosName());
					 				Map<String, String> hmap = oMapper.convertValue(valueObject, Map.class);
					 				sText = hmap.get("value");	
					 				
					 				if(sText != null){
					 					if(position.getOperation().equalsIgnoreCase("text"))
					 							{
					 								xOffset = valueMap.get(0).getxShift();
					 								yOffset = valueMap.get(0).getyShift();
					 							}
					 					else
					 					{
					 						for(PDFPositionValueMap value : valueMap){
							 					if(pdfValuesService.findById(value.getValueId()).getValue().equalsIgnoreCase(sText))
							 					{	
							 						xOffset = value.getxShift();
							 						yOffset = value.getyShift();
							 					}
							 				
							 				}
					 					}
					 				}
					 				else
					 				{sText = "";}
					 				break;
					 			case 1: 
					 				
					 				
					 				for(PDFPositionValueMap value : valueMap){
					 					if(value.getDefaultFlag())
					 					{	
					 						xOffset = value.getxShift();
					 						yOffset = value.getyShift();
					 						sText = pdfValuesService.findById(value.getValueId()).getValue();
					 					}
					 				
					 					}
					 				
					 				break;
					 		}
					 		if(position.getOperation().equalsIgnoreCase("select"))
					 		{
					 			sText = "\u2714";
					 			font = PDType1Font.ZAPF_DINGBATS;
					 			fontSize = 15;
					 		}
					 		else
					 		{font = PDType1Font.TIMES_ROMAN;
					 		fontSize = 10;
					 		}
					 			
						}
						
						
						yOffset = page.getMediaBox().getHeight() - yOffset;
						PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
						contentStream.setFont(font, fontSize);
						
						if(position.getOperation().equalsIgnoreCase("date"))
						{
							String parts[]=  sText.split("/");
						
							//Writing text on the pdf 
							contentStream.beginText();
							contentStream.newLineAtOffset(xOffset,yOffset); // coordinates of the new text
							contentStream.showText(parts[0]); 
							contentStream.endText();
							
							contentStream.beginText();
							contentStream.newLineAtOffset(xOffset+22,yOffset); // coordinates of the new text
							contentStream.showText(parts[1]); 
							contentStream.endText();
							
							
							contentStream.beginText();
							contentStream.newLineAtOffset(xOffset+44,yOffset); // coordinates of the new text
							contentStream.showText(parts[2]); 
							contentStream.endText();
							
						}
						else
						{
						
							
							//Writing text on the pdf 
							contentStream.beginText();
							contentStream.newLineAtOffset(xOffset,yOffset); // coordinates of the new text
							contentStream.showText(sText); 
							contentStream.endText();
						}
						contentStream.close();
					}
				}
			}
			//end loop 


		out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        
        byte[] data = out.toByteArray();

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(data);
	 }
	
	





}
