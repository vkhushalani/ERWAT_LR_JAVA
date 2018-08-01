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
	
	static int lineLength = 115;
	
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
					 		{// getting value from front end
					 			case 0 : 
					 				Object valueObject = inputObject.get(pos.getPosName());
					 				Map<String, String> hmap = oMapper.convertValue(valueObject, Map.class);
					 				sText = hmap.get("value");	
					 				
					 				if(sText != null){
					 					if(position.getOperation().equalsIgnoreCase("text") 
					 							|| position.getOperation().equalsIgnoreCase("date") 
					 							|| position.getOperation().equalsIgnoreCase("textarea") 
					 							|| position.getOperation().equalsIgnoreCase("multiline"))
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
					 				// for getting default value from backend(DB)
					 				
					 				for(PDFPositionValueMap value : valueMap){
					 					if(value.getDefaultFlag())
					 					{	
					 						xOffset = value.getxShift();
					 						yOffset = value.getyShift();
					 						sText = pdfValuesService.findById(value.getValueId()).getValue();
					 					}
					 				
					 					}
					 				
					 				break;
					 			case 100: 
					 				// for getting value from another position on the pdf
					 				
					 				PDFPositions frompos = pdfPositionsService.findById(position.getFromPosId());
					 				Object valueFromObject = inputObject.get(frompos.getPosName());
					 				Map<String, String> fhmap = oMapper.convertValue(valueFromObject, Map.class);
					 				sText = fhmap.get("value");
					 				
					 				if(sText != null){
					 					if(position.getOperation().equalsIgnoreCase("text") 
					 							|| position.getOperation().equalsIgnoreCase("date") 
					 							|| position.getOperation().equalsIgnoreCase("textarea")
					 							|| position.getOperation().equalsIgnoreCase("multiline"))
					 						
					 							{
					 								xOffset = valueMap.get(0).getxShift();
					 								yOffset = valueMap.get(0).getyShift();
					 							}
					 				
					 				}
					 				else
					 				{
					 					sText = "";
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
					 		{
					 			
					 		font = PDType1Font.TIMES_ROMAN;
					 		fontSize = 10;
					 		
					 		}
					 			
						}
						
						
						yOffset = page.getMediaBox().getHeight() - yOffset; // changing the y axis directions
						PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
						contentStream.setFont(font, fontSize); // setting writing font 
						
						// splitting the date value and writing on pdf with shift of x axis values
						if(position.getOperation().equalsIgnoreCase("date"))
						{
							if(sText.length() > 0){
									float inc = 0;
									String parts[]=  sText.split("/");
									for(int j=0;j<parts.length;j++){
									//Writing text on the pdf 
									contentStream.beginText();
									contentStream.newLineAtOffset(xOffset+inc,yOffset); // coordinates of the new text
									contentStream.showText(parts[j]); 
									contentStream.endText();
									inc = inc + 22;
							  
							 }
							}
						}
						// splitting the textArea value and writing on pdf with shift of y axis values
						else if (position.getOperation().equalsIgnoreCase("textarea"))
						{
							if(sText.length() > 0)
							{	
								int j = 0;
								int k = lineLength;
								float yInc = 0;
								try{
									while(true)
									{
										  String subText = sText.substring(j,j+lineLength);
										  char ch = subText.charAt(lineLength - 1);
										  
										  if(!(Character.isWhitespace(ch))){
											  while(!(Character.isWhitespace(subText.charAt(k - 1))))
											  {
												  k = k-1;
											  }
											    
										  	}
										  contentStream.beginText();
										  contentStream.newLineAtOffset(xOffset,yOffset - yInc); // coordinates of the new text
										  contentStream.showText(sText.substring(j,j+k)); 
										  contentStream.endText();
										  j = j+k;
										  k = lineLength;
										  yInc = yInc + 15;
									  }
								  
								}
								
								catch (IndexOutOfBoundsException e)
								{ 
									
									 contentStream.beginText();
									  contentStream.newLineAtOffset(xOffset,yOffset - yInc);
									  contentStream.showText(sText.substring(j)); 
									  contentStream.endText();
								}
							}
								
							
						}
						else if(position.getOperation().equalsIgnoreCase("multiline")){
							if(sText.length() > 0){
								
							  Integer splitValue = Integer.parseInt(position.getBreakPoint());
							  if(splitValue.intValue() < sText.length()){
							  String subText = sText.substring(0,splitValue);
							  char ch = subText.charAt(splitValue - 1);
							  int k = splitValue;
							  if(!(Character.isWhitespace(ch))){
								  while(!(Character.isWhitespace(subText.charAt(k - 1))))
								  {
									  k = k-1;
								  }}
							  contentStream.beginText();
							  contentStream.newLineAtOffset(xOffset,yOffset); // coordinates of the new text
							  contentStream.showText(sText.substring(0,k)); 
							  contentStream.endText();
							  
							  contentStream.beginText();
							  contentStream.newLineAtOffset(75,yOffset - 15); // coordinates of the new text
							  contentStream.showText(sText.substring(k,sText.length())); 
							  contentStream.endText();
							  }
							  else
							  {
								  contentStream.beginText();
								  contentStream.newLineAtOffset(xOffset,yOffset); // coordinates of the new text
								  contentStream.showText(sText); 
								  contentStream.endText();
							  }
							  }
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
