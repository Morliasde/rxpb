package com.rxpb.entity;

import java.sql.Timestamp;

/**
 * RxpbUserInfo entity. @author MyEclipse Persistence Tools
 */

public class RxpbUserInfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String displayName;
	private Integer roleId;
	private Integer status;
	private String email;
	private Integer competitionId;
	private Timestamp createDate;
	private String createUser;
	private Timestamp updateDate;
	private String updateUser;

	// Constructors

	/** default constructor */
	public RxpbUserInfo() {
	}

	/** minimal constructor */
	public RxpbUserInfo(String userName, String password, String displayName,
			Integer roleId, Integer status, Integer competitionId) {
		this.userName = userName;
		this.password = password;
		this.displayName = displayName;
		this.roleId = roleId;
		this.status = status;
		this.competitionId = competitionId;
	}

	/** full constructor */
	public RxpbUserInfo(String userName, String password, String displayName,
			Integer roleId, Integer status, String email,
			Integer competitionId, Timestamp createDate, String createUser,
			Timestamp updateDate, String updateUser) {
		this.userName = userName;
		this.password = password;
		this.displayName = displayName;
		this.roleId = roleId;
		this.status = status;
		this.email = email;
		this.competitionId = competitionId;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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