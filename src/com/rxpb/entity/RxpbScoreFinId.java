package com.rxpb.entity;

/**
 * RxpbScoreFinId entity. @author MyEclipse Persistence Tools
 */

public class RxpbScoreFinId implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String companyName;
	private Integer competitionId;
	private Double fatnessScore;
	private Double qualityScore;
	private Double tasteScore;

	// Constructors

	/** default constructor */
	public RxpbScoreFinId() {
	}

	/** minimal constructor */
	public RxpbScoreFinId(Integer groupId, Integer competitionId) {
		this.groupId = groupId;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbScoreFinId(Integer groupId, String companyName,
			Integer competitionId, Double fatnessScore, Double qualityScore,
			Double tasteScore) {
		this.groupId = groupId;
		this.companyName = companyName;
		this.competitionId = competitionId;
		this.fatnessScore = fatnessScore;
		this.qualityScore = qualityScore;
		this.tasteScore = tasteScore;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public Double getFatnessScore() {
		return this.fatnessScore;
	}

	public void setFatnessScore(Double fatnessScore) {
		this.fatnessScore = fatnessScore;
	}

	public Double getQualityScore() {
		return this.qualityScore;
	}

	public void setQualityScore(Double qualityScore) {
		this.qualityScore = qualityScore;
	}

	public Double getTasteScore() {
		return this.tasteScore;
	}

	public void setTasteScore(Double tasteScore) {
		this.tasteScore = tasteScore;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RxpbScoreFinId))
			return false;
		RxpbScoreFinId castOther = (RxpbScoreFinId) other;

		return ((this.getGroupId() == castOther.getGroupId()) || (this
				.getGroupId() != null && castOther.getGroupId() != null && this
				.getGroupId().equals(castOther.getGroupId())))
				&& ((this.getCompanyName() == castOther.getCompanyName()) || (this
						.getCompanyName() != null
						&& castOther.getCompanyName() != null && this
						.getCompanyName().equals(castOther.getCompanyName())))
				&& ((this.getCompetitionId() == castOther.getCompetitionId()) || (this
						.getCompetitionId() != null
						&& castOther.getCompetitionId() != null && this
						.getCompetitionId()
						.equals(castOther.getCompetitionId())))
				&& ((this.getFatnessScore() == castOther.getFatnessScore()) || (this
						.getFatnessScore() != null
						&& castOther.getFatnessScore() != null && this
						.getFatnessScore().equals(castOther.getFatnessScore())))
				&& ((this.getQualityScore() == castOther.getQualityScore()) || (this
						.getQualityScore() != null
						&& castOther.getQualityScore() != null && this
						.getQualityScore().equals(castOther.getQualityScore())))
				&& ((this.getTasteScore() == castOther.getTasteScore()) || (this
						.getTasteScore() != null
						&& castOther.getTasteScore() != null && this
						.getTasteScore().equals(castOther.getTasteScore())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37
				* result
				+ (getCompanyName() == null ? 0 : this.getCompanyName()
						.hashCode());
		result = 37
				* result
				+ (getCompetitionId() == null ? 0 : this.getCompetitionId()
						.hashCode());
		result = 37
				* result
				+ (getFatnessScore() == null ? 0 : this.getFatnessScore()
						.hashCode());
		result = 37
				* result
				+ (getQualityScore() == null ? 0 : this.getQualityScore()
						.hashCode());
		result = 37
				* result
				+ (getTasteScore() == null ? 0 : this.getTasteScore()
						.hashCode());
		return result;
	}

}