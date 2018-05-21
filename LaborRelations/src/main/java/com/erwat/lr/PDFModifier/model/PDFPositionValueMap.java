package com.erwat.lr.PDFModifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.OHS_MAP_PAGE_POSITIONS_POSITION_VALUES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({
    @NamedQuery(name = "PDFPositionValueMap.findAll", query = "SELECT pvm FROM PDFPositionValueMap pvm"),
    @NamedQuery(name = "PDFPositionValueMap.findByPosId", query = "SELECT pvm FROM PDFPositionValueMap pvm WHERE pvm.posId = :posId"),
    @NamedQuery(name = "PDFPositionValueMap.findById", query = "SELECT pvm FROM PDFPositionValueMap pvm WHERE pvm.posId = :posId AND pvm.valueId = :valueId")
})
public class PDFPositionValueMap {
	
	@Id
	@Column(name = "\"PAGE_POSITIONS.ID\"", columnDefinition = "INTEGER")
	private Integer posId;
	
	@Id
	@Column(name = "\"POSITION_VALUES.ID\"", columnDefinition = "INTEGER")
	private Integer valueId;
	
	@Column(name = "\"DEFAULT_FLAG\"", columnDefinition = "BOOLEAN")
	private Boolean defaultFlag;
	
	@Column(name = "\"XSHIFT\"",columnDefinition = "DECIMAL")
	private Float xShift;
	
	@Column(name = "\"YSHIFT\"",columnDefinition = "DECIMAL")
	private Float yShift;

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getValueId() {
		return valueId;
	}

	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}

	public Boolean getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Float getxShift() {
		return xShift;
	}

	public void setxShift(Float xShift) {
		this.xShift = xShift;
	}

	public Float getyShift() {
		return yShift;
	}

	public void setyShift(Float yShift) {
		this.yShift = yShift;
	}


}
