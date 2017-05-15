package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbCrabInfo entity. @author MyEclipse Persistence Tools
 */

public class RxpbCrabInfo implements java.io.Serializable {

	// Fields

	private Integer crabId;
	private Integer groupId;
	private Integer crabSex;
	private String crabLabel;
	private Float crabWeight;
	private Float crabLength;
	private Float crabFatness;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;
	private Integer competitionId;

	// Constructors

	/** default constructor */
	public RxpbCrabInfo() {
	}

	/** minimal constructor */
	public RxpbCrabInfo(Integer groupId, Integer crabSex, String crabLabel,
			Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.crabLabel = crabLabel;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbCrabInfo(Integer groupId, Integer crabSex, String crabLabel,
			Float crabWeight, Float crabLength, Float crabFatness,
			Timestamp createDate, String createUser, Timestamp updateDate,
			String updateUser, Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.crabLabel = crabLabel;
		this.crabWeight = crabWeight;
		this.crabLength = crabLength;
		this.crabFatness = crabFatness;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.competitionId = competitionId;
	}

	// Property accessors

	public Integer getCrabId() {
		return this.crabId;
	}

	public void setCrabId(Integer crabId) {
		this.crabId = crabId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCrabSex() {
		return this.crabSex;
	}

	public void setCrabSex(Integer crabSex) {
		this.crabSex = crabSex;
	}

	public String getCrabLabel() {
		return this.crabLabel;
	}

	public void setCrabLabel(String crabLabel) {
		this.crabLabel = crabLabel;
	}

	public Float getCrabWeight() {
		return this.crabWeight;
	}

	public void setCrabWeight(Float crabWeight) {
		this.crabWeight = crabWeight;
	}

	public Float getCrabLength() {
		return this.crabLength;
	}

	public void setCrabLength(Float crabLength) {
		this.crabLength = crabLength;
	}

	public Float getCrabFatness() {
		return this.crabFatness;
	}

	public void setCrabFatness(Float crabFatness) {
		this.crabFatness = crabFatness;
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

	public Integer getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

}