package com.ls.library.domain;

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
@Table(name = "LENDING_DETAILS")
public class Lending implements Serializable {
	

	private static final long serialVersionUID = -1550466011613181353L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_lending_details_id_seq")
    @SequenceGenerator(sequenceName = "t_lending_details_id_seq", allocationSize = 1, name = "t_lending_details_id_seq")
	private Long id;
	
	@Column(name = "ID_MEMBER")
	private Long idMember;
	
	@Column(name = "ID_BOOK")
	private Long idBook;
	
	@Column(name = "DATE_LENDING")
	private Date dateLending;
	
	@Column(name = "RETURN_DATE")
	private Date returnDate;
	
	@Column(name = "DUE_RETURN_DATE")
	private Date dueReturnDate;

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

	public Date getDueReturnDate() {
		return dueReturnDate;
	}

	public void setDueReturnDate(Date dueReturnDate) {
		this.dueReturnDate = dueReturnDate;
	}
	
	
	

}
