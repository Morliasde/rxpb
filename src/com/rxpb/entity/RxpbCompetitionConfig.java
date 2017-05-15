package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbCompetitionConfig entity. @author MyEclipse Persistence Tools
 */

public class RxpbCompetitionConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer competitionId;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;

	// Constructors

	/** default constructor */
	public RxpbCompetitionConfig() {
	}

	/** minimal constructor */
	public RxpbCompetitionConfig(Integer competitionId) {
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbCompetitionConfig(Integer competitionId, Timestamp createDate,
			String createUser, Timestamp updateDate, String updateUser) {
		this.competitionId = competitionId;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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