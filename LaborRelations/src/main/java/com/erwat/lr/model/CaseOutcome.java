package com.erwat.lr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_OUTCOMES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseOutcome.findAll", query = "SELECT ct FROM CaseOutcome ct")
		
})
public class CaseOutcome {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "\"ID\"", columnDefinition = "INTEGER")
		private Integer id;

		@Column(name = "\"DESCRIPTION\"", columnDefinition = "VARCHAR(64)")
		private String description;

		@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
		private Date sDate;
		
		@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
		private Date eDate;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "caseOutcome",targetEntity=CaseResults.class,fetch=FetchType.LAZY)
		private List<CaseResults> results;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "caseOutcome",targetEntity=CaseTypeStatusOutcomeMap.class,fetch=FetchType.LAZY)
		private List<CaseTypeStatusOutcomeMap> maps;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}


		public Date getsDate() {
			return sDate;
		}

		public void setsDate(Date sDate) {
			this.sDate = sDate;
		}

		public Date geteDate() {
			return eDate;
		}

		public void seteDate(Date eDate) {
			this.eDate = eDate;
		}

} 
	
