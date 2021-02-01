package eu.winwinit.bcc.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "articoli")

public class Articolo {

	private Integer id;
	private String nome;
	private Integer codice;
	
	public Articolo() {

	}

	public Articolo(Integer id, String nome, Integer codice) {
		super();
		this.id = id;
		this.nome = nome;
		this.codice = codice;
	}

	@Id
//	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "id", unique = true, nullable = true)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", length = 45)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "codice")
	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	@OneToMany(mappedBy = "articolo")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<OrdineArticolo> ordinearticolo;
	
}
