package com.rxpb.entity;

/**
 * RxpbScoreFin entity. @author MyEclipse Persistence Tools
 */

public class RxpbScoreFin implements java.io.Serializable {
	// Fields

		private Integer groupId;
		private String companyName;
		private Integer competitionId;
		private Double fatnessScore;
		private Double qualityScore;
		private Double tasteScore;

		// Constructors

		/** default constructor */
		public RxpbScoreFin() {
		}

		/** minimal constructor */
		public RxpbScoreFin(Integer groupId, Integer competitionId) {
			this.groupId = groupId;
			this.competitionId = competitionId;
		}

		/** full constructor */
		public RxpbScoreFin(Integer groupId, String companyName,
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
	
	/*
	 * 
	 * 
	 // Fields

	private RxpbScoreFinId id;

	// Constructors

	public RxpbScoreFin() {
	}

	public RxpbScoreFin(RxpbScoreFinId id) {
		this.id = id;
	}

	// Property accessors

	public RxpbScoreFinId getId() {
		return this.id;
	}

	public void setId(RxpbScoreFinId id) {
		this.id = id;
	}
	 * 
	 */
	

}