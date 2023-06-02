package com.ls.library.payload.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;

public class MemberDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7841482632285031999L;

	@JsonProperty("MEMBER_ID")
	private Long id;
	
	@JsonProperty("COGNOME")
	private String cognome;
	
	@JsonProperty("NOME")
	private String nome;
	
	@JsonProperty("INDIRIZZO")
	private String indirizzo;
	
	@JsonProperty("DATE_CARD")
	private Date dateCard;
	
	@JsonProperty("EXPIRATION_DATE")
	private Date expirationDate;
	
	@JsonProperty("COD_FISCALE")
	private String codFiscale;
	
	@JsonProperty("CARD_ID")
	private Long cardId;
	
	@JsonProperty("USERID")
	private String userId;
	
	@JsonProperty("PASSWORD")
	private String password;
	
	@JsonProperty("EMAIL")
	private String email;
	
	@JsonProperty("TOT_BOOK_BORROWED")
	private Long totBookBorrowed;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Date getDateCard() {
		return dateCard;
	}

	public void setDateCard(Date dateCard) {
		this.dateCard = dateCard;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public Long getCardId() {
		return cardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTotBookBorrowed() {
		return totBookBorrowed;
	}

	public void setTotBookBorrowed(Long totBookBorrowed) {
		this.totBookBorrowed = totBookBorrowed;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", cognome=" + cognome + ", nome=" + nome + ", indirizzo=" + indirizzo
				+ ", dateCard=" + dateCard + ", expirationDate=" + expirationDate + "]";
	}
	
	
}
