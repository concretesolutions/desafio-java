package com.khalilpan.beans;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonFilter("UserLogFilter")
@Entity
public class UserLog {

	@Id
	@GeneratedValue
	@JsonIgnore
	private long idForEntity;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user2; //usar para conectar com a tabela do user //should be withoud getter and setter and shoildnt be in constructor too
	
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
	
	private LocalDateTime lastLoginDate;
	private String token;
	
	public UserLog() {
	}

	public UserLog(Long id,  LocalDateTime lastLoginDate, String token) {
		this.id = id;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
		this.lastLoginDate = lastLoginDate;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		return "UserLog [id=" + id + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", lastLoginDate=" + lastLoginDate + ", token=" + token + "]";
	}
	
}
