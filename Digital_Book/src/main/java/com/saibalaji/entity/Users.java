package com.saibalaji.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="TBL_USER")
public class Users {
	
	

	@Id
	@GeneratedValue
	@Column(name ="USER_ID")
	Integer userId;

	@Column(name ="USER_NAME")
	String userName;

	@Column(name ="PAZZWORD")
	String pazzword;

	@Column(name="EMAIL_ID")
	String emailId;    

	@Column(name="PHONE_NUMBER")
	Long phnNumber;

	@Column(name="ROLE_ID_FK")
	Integer roleId;

	@Column(name="IS_ACTIVE")
	Boolean isActive;
	
	

	public Users() {
		super();
	}

	public Users(Integer userId, String userName, String pazzword, String emailId, Long phnNumber, Integer roleId,
			Boolean isActive) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.pazzword = pazzword;
		this.emailId = emailId;
		this.phnNumber = phnNumber;
		this.roleId = roleId;
		this.isActive = isActive;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPazzword() {
		return pazzword;
	}

	public void setPazzword(String pazzword) {
		this.pazzword = pazzword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhnNumber() {
		return phnNumber;
	}

	public void setPhnNumber(Long phnNumber) {
		this.phnNumber = phnNumber;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", pazzword=" + pazzword + ", emailId=" + emailId
				+ ", phnNumber=" + phnNumber + ", roleId=" + roleId + ", isActive=" + isActive + "]";
	}
	
	
}
