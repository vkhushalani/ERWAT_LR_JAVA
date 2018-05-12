package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_PDF_BUILDER_LABELS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFLabels.findAll", query = "SELECT pv FROM PDFLabels pv")
})
public class PDFLabels {
	
	@Id
	@Column(name = "\"ID\"", columnDefinition = "VARCHAR(32)")
	private String id;
	
	@Column(name = "\"PLACEHOLDER\"", columnDefinition = "VARCHAR(64)")
	private String placeholder;
	
	@Column(name = "\"SEARCH_LABEL\"",columnDefinition = "VARCHAR(512)")
	private String searchLabel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getSearchLabel() {
		return searchLabel;
	}

	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}

}
