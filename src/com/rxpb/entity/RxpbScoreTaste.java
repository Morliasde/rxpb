package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbScoreTaste entity. @author MyEclipse Persistence Tools
 */

public class RxpbScoreTaste implements java.io.Serializable {

	// Fields

	private Integer scoreId;
	private Integer groupId;
	private Integer crabSex;
	private Integer userId;
	private Float scoreFin;
	private Float scoreYgys;
	private Float scoreSys;
	private Float scoreGhys;
	private Float scoreXwxw;
	private Float scoreGh;
	private Float scoreFbjr;
	private Float scoreBzjr;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;
	private Integer competitionId;

	// Constructors

	/** default constructor */
	public RxpbScoreTaste() {
	}

	/** minimal constructor */
	public RxpbScoreTaste(Integer groupId, Integer crabSex, Integer userId,
			Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.userId = userId;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbScoreTaste(Integer groupId, Integer crabSex, Integer userId,
			Float scoreFin, Float scoreYgys, Float scoreSys, Float scoreGhys,
			Float scoreXwxw, Float scoreGh, Float scoreFbjr, Float scoreBzjr,
			Timestamp createDate, String createUser, Timestamp updateDate,
			String updateUser, Integer competitionId) {
		this.groupId = groupId;
		this.crabSex = crabSex;
		this.userId = userId;
		this.scoreFin = scoreFin;
		this.scoreYgys = scoreYgys;
		this.scoreSys = scoreSys;
		this.scoreGhys = scoreGhys;
		this.scoreXwxw = scoreXwxw;
		this.scoreGh = scoreGh;
		this.scoreFbjr = scoreFbjr;
		this.scoreBzjr = scoreBzjr;
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

	public Float getScoreYgys() {
		return this.scoreYgys;
	}

	public void setScoreYgys(Float scoreYgys) {
		this.scoreYgys = scoreYgys;
	}

	public Float getScoreSys() {
		return this.scoreSys;
	}

	public void setScoreSys(Float scoreSys) {
		this.scoreSys = scoreSys;
	}

	public Float getScoreGhys() {
		return this.scoreGhys;
	}

	public void setScoreGhys(Float scoreGhys) {
		this.scoreGhys = scoreGhys;
	}

	public Float getScoreXwxw() {
		return this.scoreXwxw;
	}

	public void setScoreXwxw(Float scoreXwxw) {
		this.scoreXwxw = scoreXwxw;
	}

	public Float getScoreGh() {
		return this.scoreGh;
	}

	public void setScoreGh(Float scoreGh) {
		this.scoreGh = scoreGh;
	}

	public Float getScoreFbjr() {
		return this.scoreFbjr;
	}

	public void setScoreFbjr(Float scoreFbjr) {
		this.scoreFbjr = scoreFbjr;
	}

	public Float getScoreBzjr() {
		return this.scoreBzjr;
	}

	public void setScoreBzjr(Float scoreBzjr) {
		this.scoreBzjr = scoreBzjr;
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