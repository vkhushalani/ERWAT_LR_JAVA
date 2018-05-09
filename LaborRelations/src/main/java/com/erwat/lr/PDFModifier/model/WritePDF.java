package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_PDF_BUILDER\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "WritePDF.findAll", query = "SELECT wp FROM WritePDF wp"),
    @NamedQuery(name = "WritePDF.findByPageNoOfDoc", query = "SELECT wp FROM WritePDF wp WHERE wp.pageNo = :pageNo AND wp.docName = :dname"),
})
public class WritePDF {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"PAGE_NO\"",columnDefinition = "INTEGER")
	private Integer pageNo; 
	
	@Column(name = "\"DOC_NAME\"",columnDefinition = "VARCHAR(64)")
	private String docName;
	
	@Column(name = "\"SEARCH_LABEL\"",columnDefinition = "VARCHAR(64)")
	private String searchLabel;
	
	@Column(name = "\"XSHIFT\"",columnDefinition = "DECIMAL")
	private Float XShift;
	
	@Column(name = "\"YSHIFT\"",columnDefinition = "DECIMAL")
	private Float YShift;
	
	@Column(name = "\"INDEX\"",columnDefinition = "INTEGER")
	private Integer searchLabelIndex;
	
	@Column(name = "\"DIRECTION\"",columnDefinition = "VARCHAR(1)")
	private String relativeDir;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearchLabel() {
		return searchLabel;
	}

	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
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
	

}
