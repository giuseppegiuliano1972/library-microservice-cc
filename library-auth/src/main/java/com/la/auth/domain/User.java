package com.la.auth.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIBRARY_USER_AUTH")
public class User implements Serializable {

	private static final long serialVersionUID = 2357421982476497742L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_id_seq")
    @SequenceGenerator(sequenceName = "t_user_id_seq", allocationSize = 1, name = "t_user_id_seq")
	private Long id;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "password")
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
