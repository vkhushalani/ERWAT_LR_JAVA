package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.OHS_PAGE_POSITIONS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFPositions.findAll", query = "SELECT pp FROM PDFPositions pp"),
    @NamedQuery(name = "PDFPositions.findByPosName", query = "SELECT pp FROM PDFPositions pp WHERE pp.posName = :posName")
})
public class PDFPositions {
	
	@Id
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"NAME\"",columnDefinition = "VARCHAR(64)")
	private String posName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

}
