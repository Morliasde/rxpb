package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbCompetitionInfo entity. @author MyEclipse Persistence Tools
 */

public class RxpbCompetitionInfo implements java.io.Serializable {

	// Fields

	private Integer competitionId;
	private String competitionYear;
	private Float varFatnessM;
	private Float varFatnessF;
	private Float varWeightM;
	private Float varWeightF;
	private Float varMfatnessSd;
	private Float varMweightSd;
	private Float varFfatnessSd;
	private Float varFweightSd;
	private Integer resultFatness;
	private Integer resultQuality;
	private Integer resultTaste;
	private String note;
	private Integer status;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;

	// Constructors

	/** default constructor */
	public RxpbCompetitionInfo() {
	}

	/** minimal constructor */
	public RxpbCompetitionInfo(String competitionYear, Float varFatnessM,
			Float varFatnessF, Float varWeightM, Float varWeightF,
			Float varMfatnessSd, Float varMweightSd, Float varFfatnessSd,
			Float varFweightSd, Integer resultFatness, Integer resultQuality,
			Integer resultTaste, Integer status) {
		this.competitionYear = competitionYear;
		this.varFatnessM = varFatnessM;
		this.varFatnessF = varFatnessF;
		this.varWeightM = varWeightM;
		this.varWeightF = varWeightF;
		this.varMfatnessSd = varMfatnessSd;
		this.varMweightSd = varMweightSd;
		this.varFfatnessSd = varFfatnessSd;
		this.varFweightSd = varFweightSd;
		this.resultFatness = resultFatness;
		this.resultQuality = resultQuality;
		this.resultTaste = resultTaste;
		this.status = status;
	}

	/** full constructor */
	public RxpbCompetitionInfo(String competitionYear, Float varFatnessM,
			Float varFatnessF, Float varWeightM, Float varWeightF,
			Float varMfatnessSd, Float varMweightSd, Float varFfatnessSd,
			Float varFweightSd, Integer resultFatness, Integer resultQuality,
			Integer resultTaste, String note, Integer status,
			Timestamp createDate, String createUser, Timestamp updateDate,
			String updateUser) {
		this.competitionYear = competitionYear;
		this.varFatnessM = varFatnessM;
		this.varFatnessF = varFatnessF;
		this.varWeightM = varWeightM;
		this.varWeightF = varWeightF;
		this.varMfatnessSd = varMfatnessSd;
		this.varMweightSd = varMweightSd;
		this.varFfatnessSd = varFfatnessSd;
		this.varFweightSd = varFweightSd;
		this.resultFatness = resultFatness;
		this.resultQuality = resultQuality;
		this.resultTaste = resultTaste;
		this.note = note;
		this.status = status;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public String getCompetitionYear() {
		return this.competitionYear;
	}

	public void setCompetitionYear(String competitionYear) {
		this.competitionYear = competitionYear;
	}

	public Float getVarFatnessM() {
		return this.varFatnessM;
	}

	public void setVarFatnessM(Float varFatnessM) {
		this.varFatnessM = varFatnessM;
	}

	public Float getVarFatnessF() {
		return this.varFatnessF;
	}

	public void setVarFatnessF(Float varFatnessF) {
		this.varFatnessF = varFatnessF;
	}

	public Float getVarWeightM() {
		return this.varWeightM;
	}

	public void setVarWeightM(Float varWeightM) {
		this.varWeightM = varWeightM;
	}

	public Float getVarWeightF() {
		return this.varWeightF;
	}

	public void setVarWeightF(Float varWeightF) {
		this.varWeightF = varWeightF;
	}

	public Float getVarMfatnessSd() {
		return this.varMfatnessSd;
	}

	public void setVarMfatnessSd(Float varMfatnessSd) {
		this.varMfatnessSd = varMfatnessSd;
	}

	public Float getVarMweightSd() {
		return this.varMweightSd;
	}

	public void setVarMweightSd(Float varMweightSd) {
		this.varMweightSd = varMweightSd;
	}

	public Float getVarFfatnessSd() {
		return this.varFfatnessSd;
	}

	public void setVarFfatnessSd(Float varFfatnessSd) {
		this.varFfatnessSd = varFfatnessSd;
	}

	public Float getVarFweightSd() {
		return this.varFweightSd;
	}

	public void setVarFweightSd(Float varFweightSd) {
		this.varFweightSd = varFweightSd;
	}

	public Integer getResultFatness() {
		return this.resultFatness;
	}

	public void setResultFatness(Integer resultFatness) {
		this.resultFatness = resultFatness;
	}

	public Integer getResultQuality() {
		return this.resultQuality;
	}

	public void setResultQuality(Integer resultQuality) {
		this.resultQuality = resultQuality;
	}

	public Integer getResultTaste() {
		return this.resultTaste;
	}

	public void setResultTaste(Integer resultTaste) {
		this.resultTaste = resultTaste;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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