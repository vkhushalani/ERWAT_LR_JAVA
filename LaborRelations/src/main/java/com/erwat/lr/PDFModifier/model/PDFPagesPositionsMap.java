package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.OHS_MAP_PDF_PAGES_PAGE_POSITIONS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFPagesPositionsMap.findAll", query = "SELECT ppm FROM PDFPagesPositionsMap ppm"),
    @NamedQuery(name = "PDFPagesPositionsMap.findByPageId", query = "SELECT ppm FROM PDFPagesPositionsMap ppm WHERE ppm.pageId = :pageId "),
    @NamedQuery(name = "PDFPagesPositionsMap.findById", query = "SELECT ppm FROM PDFPagesPositionsMap ppm WHERE ppm.pageId = :pageId AND ppm.posId = :posId ")
})
public class PDFPagesPositionsMap {
	
	@Id
	@Column(name = "\"PAGES.ID\"", columnDefinition = "INTEGER")
	private Integer pageId;
	
	@Id
	@Column(name = "\"PAGE_POSITIONS.ID\"", columnDefinition = "INTEGER")
	private Integer posId;
	
	@Column(name = "\"FROM_SYSTEM\"",columnDefinition = "INTEGER")
	private Integer fromSystem;
	
	@Column(name = "\"WRITE_OPERATION\"",columnDefinition = "VARCHAR(32)")
	private String operation;
	
	@Column(name = "\"VALUE_FROM_POSITION.ID\"", columnDefinition = "INTEGER")
	private Integer fromPosId;
	
	@Column(name = "\"BREAK_POINT\"", columnDefinition = "VARCHAR(512)")
	private String breakPoint;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(Integer fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getFromPosId() {
		return fromPosId;
	}

	public void setFromPosId(Integer fromPosId) {
		this.fromPosId = fromPosId;
	}

	public String getBreakPoint() {
		return breakPoint;
	}

	public void setBreakPoint(String breakPoint) {
		this.breakPoint = breakPoint;
	}

}
