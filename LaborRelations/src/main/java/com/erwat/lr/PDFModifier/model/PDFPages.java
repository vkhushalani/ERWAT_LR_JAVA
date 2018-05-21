package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.OHS_PDF_PAGES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFPages.findAll", query = "SELECT pp FROM PDFPages pp")
})
public class PDFPages {
	
	@Id
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"NAME\"",columnDefinition = "VARCHAR(64)")
	private String pageName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
