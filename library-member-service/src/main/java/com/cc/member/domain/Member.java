package com.cc.member.domain;

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
@Table(name = "T_LIB_MEMBER")
public class Member implements Serializable {
	

	private static final long serialVersionUID = -1550466011613181353L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_lib_memebr_id_seq")
    @SequenceGenerator(sequenceName = "t_lib_memebr_id_seq", allocationSize = 1, name = "t_lib_memebr_id_seq")
	private Long id;
	
	@Column(name = "cod_fiscale")
	private String codFiscale;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@Column(name = "card_id")
	private Long cardId;
	
	@Column(name = "data_card")
	private Date dateCard;
	
	@Column(name = "scadenza_member")
	private Date expirationDate;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

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

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
