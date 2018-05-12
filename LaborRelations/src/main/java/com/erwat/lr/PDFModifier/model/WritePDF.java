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
    @NamedQuery(name = "WritePDF.findByDocName", query = "SELECT wp FROM WritePDF wp WHERE wp.docName = :dname")
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
	

}
