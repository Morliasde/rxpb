package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbCompanyInfo entity. @author MyEclipse Persistence Tools
 */

public class RxpbCompanyInfo implements java.io.Serializable {

	// Fields

	private Integer companyId;
	private String companyName;
	private Integer competitionId;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;

	// Constructors

	/** default constructor */
	public RxpbCompanyInfo() {
	}

	/** minimal constructor */
	public RxpbCompanyInfo(String companyName, Integer competitionId) {
		this.companyName = companyName;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbCompanyInfo(String companyName, Integer competitionId,
			Timestamp createDate, String createUser, Timestamp updateDate,
			String updateUser) {
		this.companyName = companyName;
		this.competitionId = competitionId;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}