package com.cc.library.payload.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDto implements Serializable {
	
private static final long serialVersionUID = 2825762375681813104L;
	
	@JsonProperty("BOOK_ID")
	private Long id;
	
	@JsonProperty("TITOLO")
	private String titolo;
	
	@JsonProperty("AUTORE")
	private String autore;
	
	@JsonProperty("EDITORE")
	private String editore;
	
	@JsonProperty("ANNO_PUB")
	private String anno;
	
	@JsonProperty("DESCRIZIONE")
	private String descrizione;
	
	@JsonProperty("DISPONIBILE")
	private Long disponibile;

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

	public Long getDisponibile() {
		return disponibile;
	}

	public void setDisponibile(Long disponibile) {
		this.disponibile = disponibile;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", editore=" + editore + ", anno="
				+ anno + ", descrizione=" + descrizione + "]";
	}
	
}
