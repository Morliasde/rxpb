package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbScoreQuality entity. @author MyEclipse Persistence Tools
 */

public class RxpbScoreQuality implements java.io.Serializable {

	// Fields

	private Integer scoreId;
	private Integer groupId;
	private Integer crabSex;
	private Integer userId;
	private Float scoreFin;
	private Float scoreBts;
	private Float scoreFts;
	private Float scoreEc;
	private Float scoreDscc;
	private Float scoreBbyzt;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;
	private Integer competitionId;

	// Constructors

	/** default constructor */
	public RxpbScoreQuality() {
	}

	/** minimal constructor */
	public RxpbScoreQuality(Integer groupId, Integer crabSex, Integer userId,
			Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.userId = userId;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbScoreQuality(Integer groupId, Integer crabSex, Integer userId,
			Float scoreFin, Float scoreBts, Float scoreFts, Float scoreEc,
			Float scoreDscc, Float scoreBbyzt, Timestamp createDate,
			String createUser, Timestamp updateDate, String updateUser,
			Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.userId = userId;
		this.scoreFin = scoreFin;
		this.scoreBts = scoreBts;
		this.scoreFts = scoreFts;
		this.scoreEc = scoreEc;
		this.scoreDscc = scoreDscc;
		this.scoreBbyzt = scoreBbyzt;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.competitionId = competitionId;
	}

	// Property accessors

	public Integer getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getScoreFin() {
		return this.scoreFin;
	}

	public void setScoreFin(Float scoreFin) {
		this.scoreFin = scoreFin;
	}

	public Float getScoreBts() {
		return this.scoreBts;
	}

	public void setScoreBts(Float scoreBts) {
		this.scoreBts = scoreBts;
	}

	public Float getScoreFts() {
		return this.scoreFts;
	}

	public void setScoreFts(Float scoreFts) {
		this.scoreFts = scoreFts;
	}

	public Float getScoreEc() {
		return this.scoreEc;
	}

	public void setScoreEc(Float scoreEc) {
		this.scoreEc = scoreEc;
	}

	public Float getScoreDscc() {
		return this.scoreDscc;
	}

	public void setScoreDscc(Float scoreDscc) {
		this.scoreDscc = scoreDscc;
	}

	public Float getScoreBbyzt() {
		return this.scoreBbyzt;
	}

	public void setScoreBbyzt(Float scoreBbyzt) {
		this.scoreBbyzt = scoreBbyzt;
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