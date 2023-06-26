package com.la.auth.payload.response;

public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String userid;

	public JwtResponse(String accessToken, Long id, String userid) {
		this.token = accessToken;
		this.id = id;
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
	
	
}
