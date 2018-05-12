package com.erwat.lr.PDFModifier.controller;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.PDFModifier.Extension.GetPhraseAndLocation;
import com.erwat.lr.PDFModifier.model.PDFLabels;
import com.erwat.lr.PDFModifier.model.PDFLabelsMap;
import com.erwat.lr.PDFModifier.model.WritePDF;
import com.erwat.lr.PDFModifier.service.PDFLabelsMapService;
import com.erwat.lr.PDFModifier.service.PDFLabelsService;
import com.erwat.lr.PDFModifier.service.WritePDFService;

import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/PDFBuilder")
public class PDFModifier {
	
	Logger logger = LoggerFactory.getLogger(PDFModifier.class);
	 @Autowired
	 WritePDFService writePDFService;
	 
	 @Autowired
	 PDFLabelsMapService pdfLabelsMapService;
	 
	 @Autowired
	 PDFLabelsService pdfLabelsService;
	 
	 @GetMapping("/values/{filename}")
		public ResponseEntity<?> getAllDefaultValues(@PathVariable("filename") String fName) {
		 List<PDFLabelsMap> values = new ArrayList<PDFLabelsMap>();	
		 List<WritePDF> docValues = writePDFService.findByDocName(fName);
		 	for(WritePDF docValue : docValues)
		 	{
		 		List<PDFLabelsMap> mapValues =  pdfLabelsMapService.findByPDFId(docValue.getId());
		 		values.addAll(mapValues);
		 		
		 	}
		 	JSONObject robj =  new JSONObject();
		 	for(PDFLabelsMap value :values)
		 	{
		 		JSONObject vobj = new JSONObject();
		 		vobj.put("value", value.getValue());
		 		vobj.put("defaultFlag", value.getDefaultFlag());
		 		PDFLabels label = pdfLabelsService.findById(value.getLabelID());
		 		vobj.put("label", label.getSearchLabel());
		 		vobj.put("placeholder", label.getPlaceholder());
		 		robj.put(value.getLabelID(), vobj);
		 		
		 	}
		 	
			return ResponseEntity.ok().body(robj.toString());
			
		}
	@PostMapping("/generate/{filename}")
	public ResponseEntity<?> modifyPDF(@PathVariable("filename") String fName,HttpServletResponse response,@RequestBody JSONObject inputObject) throws InvalidPasswordException, IOException, PrinterException{
		
		ByteArrayOutputStream out = null;
		
		float fontSize = 12;
		PDFont font = PDType1Font.HELVETICA_BOLD;
		
		InputStream is = getClass().getResourceAsStream("/Files/"+fName+".pdf");
		
		PDDocument document  = PDDocument.load(is);
		
		PDPageTree pages = document.getPages();
		PDPage page;
		WritePDF wPDF;
		//loop the pages 
		for (int i=0;i< document.getNumberOfPages();i++)
		{	
			page = pages.get(i);
			wPDF = writePDFService.findByPageNoOfDoc(i+1,fName);
			List<PDFLabelsMap> items = pdfLabelsMapService.findByPDFId(wPDF.getId());
			
			// loop for all the pdf form labels per page
			for(PDFLabelsMap item :items){
				float xOffset = 0;
				float yOffset = 0;
				
				PDFLabels label = pdfLabelsService.findById(item.getLabelID());
				// search the coordinates of the identifier and than accordingly determine coordinates of the new text 
				List<Float[]> coordinates = getWordLocation(wPDF.getPageNo(),document,label.getSearchLabel(),item.getSearchLabelIndex());
				//Calculating the xOffset and yOffset using item xShift and yShift Values
				if(coordinates.size() != 0){
				if(item.getRelativeDir().equalsIgnoreCase("R")){
				xOffset = coordinates.get(label.getSearchLabel().length())[0] + item.getXShift();
				yOffset = coordinates.get(label.getSearchLabel().length())[1] + item.getYShift();
				}
				else
				{ logger.debug("coordinates else");
					
					xOffset = coordinates.get(0)[0] + item.getXShift();
					yOffset = coordinates.get(0)[1] + item.getYShift();
					logger.debug("X label:"+ xOffset);
					logger.debug("Y label:"+ yOffset);
				}
				yOffset = page.getMediaBox().getHeight() - yOffset;
				}	
				PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
				contentStream.setFont(font, fontSize);
				logger.debug("X write:"+xOffset);
				logger.debug("Y write:"+yOffset);
				//Writing text on the pdf 
				contentStream.beginText();
				contentStream.newLineAtOffset(xOffset,yOffset); // coordinates of the new text
				JSONObject valueObject = inputObject.getJSONObject(label.getId());
				String sText = "";
				if(!item.getDefaultFlag()){
				 sText = (String) valueObject.getString("value");}
				else
				{
					sText = item.getValue();
				}
				contentStream.showText(sText); 
				contentStream.endText();
				contentStream.close(); 
				}
			//end loop 
			
				
		
		}
		//end loop
		
		out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        
        byte[] data = out.toByteArray();
        is = new ByteArrayInputStream(data);
		
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename='"+fName+".pdf'");
        
        IOUtils.copy(is, response.getOutputStream());
        IOUtils.closeQuietly(is);
		

		return ResponseEntity.ok().body("Success");
	 }

	

	private List<Float[]> getWordLocation(Integer pageNo, PDDocument file,String searchLabel, Integer indexValue) throws IOException {
		PDDocument document = null;
		List<TextPosition> locations = null;
		try {
            
			PDFTextStripper stripper = new GetPhraseAndLocation();
            document = file;
            stripper.setSortByPosition(true);
            stripper.setStartPage(pageNo);
            stripper.setEndPage(pageNo);
 
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            locations = ((GetPhraseAndLocation) stripper).getPositions();
        }
        finally {
        	
            
        }
		String searchString = searchLabel;
		int labelIndex = indexValue.intValue();
		List<Float[]> coordinates = new ArrayList<Float[]>();
		int j=0,index = 0;
		if(locations !=null){
        for (TextPosition text : locations)
        	{	
        		String sLetter = Character.toString(searchString.charAt(j));
        		if(text.getUnicode().equals(sLetter))
        		{ 
        			j = j+1;
        			
        			Float [] cc = new Float[2] ;
    				cc[0] = text.getXDirAdj();
    				cc[1] = text.getYDirAdj();
  
    				coordinates.add(cc);
        			
    				if(j == searchString.length())
        			{	
        				if(labelIndex == index)
        					break;
        				else
        				{ index = index + 1;
	        				coordinates.clear();
	            			j =0;
        				}
        			}
        		}
        		else
        		{coordinates.clear();
        			j =0;
        		}
        	}}
		return coordinates;
        
	}


}
