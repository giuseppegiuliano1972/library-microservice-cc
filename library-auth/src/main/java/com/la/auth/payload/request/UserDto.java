package com.la.auth.payload.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5868741011102360433L;
	
	@JsonProperty("AUTH_ID")
	private Long id;
	
	@JsonProperty("USERID")
	private String userid;
	
	@JsonProperty("PASSWORD")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	



}
