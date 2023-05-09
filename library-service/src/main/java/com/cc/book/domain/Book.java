package com.cc.book.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_BOOK")
public class Book implements Serializable{


	private static final long serialVersionUID = 3930279565540913028L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_BOOK_ID_SEQ")
    @SequenceGenerator(sequenceName = "T_BOOK_ID_SEQ", allocationSize = 1, name = "T_BOOK_ID_SEQ")
	private Long id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "autore")
	private String autore;
	
	@Column(name = "editore")
	private String editore;
	
	@Column(name = "anno")
	private String anno;
	
	@Column(name = "descrizione")
	private String descrizione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = editore;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", editore=" + editore + ", anno="
				+ anno + ", descrizione=" + descrizione + "]";
	}
	
	
	

}
