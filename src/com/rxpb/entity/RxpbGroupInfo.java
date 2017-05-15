package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbGroupInfo entity. @author MyEclipse Persistence Tools
 */

public class RxpbGroupInfo implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private Integer companyId;
	private Integer competitionId;
	private Float fatnessScoreM;
	private Float qualityScoreM;
	private Float tasteScoreM;
	private Float fatnessScoreF;
	private Float qualityScoreF;
	private Float tasteScoreF;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;

	// Constructors

	/** default constructor */
	public RxpbGroupInfo() {
	}

	/** minimal constructor */
	public RxpbGroupInfo(Integer companyId, Integer competitionId) {
		this.companyId = companyId;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbGroupInfo(Integer companyId, Integer competitionId,
			Float fatnessScoreM, Float qualityScoreM, Float tasteScoreM,
			Float fatnessScoreF, Float qualityScoreF, Float tasteScoreF,
			Timestamp createDate, String createUser, Timestamp updateDate,
			String updateUser) {
		this.companyId = companyId;
		this.competitionId = competitionId;
		this.fatnessScoreM = fatnessScoreM;
		this.qualityScoreM = qualityScoreM;
		this.tasteScoreM = tasteScoreM;
		this.fatnessScoreF = fatnessScoreF;
		this.qualityScoreF = qualityScoreF;
		this.tasteScoreF = tasteScoreF;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public Float getFatnessScoreM() {
		return this.fatnessScoreM;
	}

	public void setFatnessScoreM(Float fatnessScoreM) {
		this.fatnessScoreM = fatnessScoreM;
	}

	public Float getQualityScoreM() {
		return this.qualityScoreM;
	}

	public void setQualityScoreM(Float qualityScoreM) {
		this.qualityScoreM = qualityScoreM;
	}

	public Float getTasteScoreM() {
		return this.tasteScoreM;
	}

	public void setTasteScoreM(Float tasteScoreM) {
		this.tasteScoreM = tasteScoreM;
	}

	public Float getFatnessScoreF() {
		return this.fatnessScoreF;
	}

	public void setFatnessScoreF(Float fatnessScoreF) {
		this.fatnessScoreF = fatnessScoreF;
	}

	public Float getQualityScoreF() {
		return this.qualityScoreF;
	}

	public void setQualityScoreF(Float qualityScoreF) {
		this.qualityScoreF = qualityScoreF;
	}

	public Float getTasteScoreF() {
		return this.tasteScoreF;
	}

	public void setTasteScoreF(Float tasteScoreF) {
		this.tasteScoreF = tasteScoreF;
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