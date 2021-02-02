package eu.winwinit.bcc.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "articoli")

public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id", unique = true, nullable = true)
	private Integer id;

	@Column(name = "nome", length = 45)
	private String nome;

	@Column(name = "codice")
	private Integer codice;

	@JsonIgnore
	@ElementCollection(targetClass=OrdineArticolo.class)
	@OneToMany(mappedBy = "articolo")
	private List<OrdineArticolo> ordineArticolo;

	public Articolo() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	@JsonIgnore
	public List<OrdineArticolo> getOrdineArticolo() {
		return ordineArticolo;
	}

	public void setOrdinearticolo(List<OrdineArticolo> ordineArticolo) {
		this.ordineArticolo = ordineArticolo;
	}



}
