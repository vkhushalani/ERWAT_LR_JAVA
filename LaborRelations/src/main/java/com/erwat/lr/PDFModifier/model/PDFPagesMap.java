package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.OHS_MAP_PDFS_PDF_PAGES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFPagesMap.findAll", query = "SELECT ppm FROM PDFPagesMap ppm"),
    @NamedQuery(name = "PDFPagesMap.findByPdfId", query = "SELECT ppm FROM PDFPagesMap ppm WHERE ppm.pdfId = :pdfId"),
    @NamedQuery(name = "PDFPagesMap.findById", query = "SELECT ppm FROM PDFPagesMap ppm WHERE ppm.pageId = :pageId AND ppm.pdfId = :pdfId"),
    @NamedQuery(name = "PDFPagesMap.findByPageNoId", query = "SELECT ppm FROM PDFPagesMap ppm WHERE ppm.pageNo = :pageNo AND ppm.pdfId = :pdfId")
})
public class PDFPagesMap {
	
	@Id
	@Column(name = "\"PDFS.ID\"", columnDefinition = "INTEGER")
	private Integer pdfId;
	
	@Id
	@Column(name = "\"PAGES.ID\"", columnDefinition = "INTEGER")
	private Integer pageId;
	
	@Column(name = "\"PAGE_NO\"", columnDefinition = "INTEGER")
	private Integer pageNo;

	public Integer getPdfId() {
		return pdfId;
	}

	public void setPdfId(Integer pdfId) {
		this.pdfId = pdfId;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
