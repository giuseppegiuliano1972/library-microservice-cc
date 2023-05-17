package com.cc.library.payload.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LendingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 697232104023758950L;

	@JsonProperty("LEND_ID")
	private Long id;
	
	@JsonProperty("ID_MEMBER")
	private Long idMember;
	
	@JsonProperty("USERID")
	private String userid;
	
	@JsonProperty("BOOK_ID")
	private Long idBook;
	
	@JsonProperty("DATE_LENDING")
	private Date dateLending;
	
	@JsonProperty("RETURN_DATE")
	private Date returnDate;
	
	@JsonProperty("COD_FISCALE")
	private String codFiscale;
	
	@JsonProperty("STATUS")
	private String status;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

	public Date getDateLending() {
		return dateLending;
	}

	public void setDateLending(Date dateLending) {
		this.dateLending = dateLending;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "LendingDto [id=" + id + ", idMember=" + idMember + ", idBook=" + idBook + ", dateLending=" + dateLending
				+ ", returnDate=" + returnDate + ", codFiscale=" + codFiscale + ", status=" + status + "]";
	}
	
	

}
