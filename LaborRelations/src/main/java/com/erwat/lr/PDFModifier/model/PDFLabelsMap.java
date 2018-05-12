package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_PDF_BUILDER_LABELS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFLabelsMap.findAll", query = "SELECT pv FROM PDFLabelsMap pv"),
    @NamedQuery(name = "PDFLabelsMap.findById", query = "SELECT pv FROM PDFLabelsMap pv WHERE pv.pdfID = :pdfID AND pv.labelID = :labelID"),
    @NamedQuery(name = "PDFLabelsMap.findByLabelId", query = "SELECT pv FROM PDFLabelsMap pv WHERE pv.labelID = :labelID"),
    @NamedQuery(name = "PDFLabelsMap.findByPDFId", query = "SELECT pv FROM PDFLabelsMap pv WHERE pv.pdfID = :pdfID")
})
public class PDFLabelsMap {
	
	@Id
	@Column(name = "\"PDF_BUILDER.ID\"", columnDefinition = "INTEGER")
	private Integer pdfID;
	
	@Id
	@Column(name = "\"PDF_BUILDER_LABELS.ID\"", columnDefinition = "VARCHAR(32)")
	private String labelID;
	
	@Column(name = "\"DEFAULT\"",columnDefinition = "BOOLEAN")
	private Boolean defaultFlag;
	
	@Column(name = "\"VALUE\"",columnDefinition = "VARCHAR(64)")
	private String value;
	
	@Column(name = "\"XSHIFT\"",columnDefinition = "DECIMAL")
	private Float XShift;
	
	@Column(name = "\"YSHIFT\"",columnDefinition = "DECIMAL")
	private Float YShift;
	
	@Column(name = "\"INDEX\"",columnDefinition = "INTEGER")
	private Integer searchLabelIndex;
	
	@Column(name = "\"DIRECTION\"",columnDefinition = "VARCHAR(1)")
	private String relativeDir;

	public Integer getPdfID() {
		return pdfID;
	}

	public void setPdfID(Integer pdfID) {
		this.pdfID = pdfID;
	}

	public String getLabelID() {
		return labelID;
	}

	public void setLabelID(String labelID) {
		this.labelID = labelID;
	}

	public Boolean getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Float getXShift() {
		return XShift;
	}

	public void setXShift(Float xShift) {
		XShift = xShift;
	}

	public Float getYShift() {
		return YShift;
	}

	public void setYShift(Float yShift) {
		YShift = yShift;
	}

	public Integer getSearchLabelIndex() {
		return searchLabelIndex;
	}

	public void setSearchLabelIndex(Integer searchLabelIndex) {
		this.searchLabelIndex = searchLabelIndex;
	}

	public String getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(String relativeDir) {
		this.relativeDir = relativeDir;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
