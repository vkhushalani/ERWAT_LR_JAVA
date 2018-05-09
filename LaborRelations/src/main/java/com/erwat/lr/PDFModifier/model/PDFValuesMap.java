package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_PDF_BUILDER_DEFAULT_VALUES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFValuesMap.findAll", query = "SELECT pv FROM PDFValuesMap pv"),
    @NamedQuery(name = "PDFValuesMap.findById", query = "SELECT pv FROM PDFValuesMap pv WHERE pv.pdfID = :pdfID AND pv.valueID = :valueID"),
    @NamedQuery(name = "PDFValuesMap.findByValueId", query = "SELECT pv FROM PDFValuesMap pv WHERE pv.valueID = :valueID"),
    @NamedQuery(name = "PDFValuesMap.findByPDFId", query = "SELECT pv FROM PDFValuesMap pv WHERE pv.pdfID = :pdfID")
})
public class PDFValuesMap {
	
	@Id
	@Column(name = "\"PDF_BUILDER.ID\"", columnDefinition = "INTEGER")
	private Integer pdfID;
	
	@Column(name = "\"PDF_BUILDER_DEFAULT_VALUES.ID\"", columnDefinition = "VARCHAR(32)")
	private String valueID;

	public Integer getPdfID() {
		return pdfID;
	}

	public void setPdfID(Integer pdfID) {
		this.pdfID = pdfID;
	}

	public String getValueID() {
		return valueID;
	}

	public void setValueID(String valueID) {
		this.valueID = valueID;
	}
}
