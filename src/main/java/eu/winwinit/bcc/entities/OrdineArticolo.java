package eu.winwinit.bcc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ordini_articoli")
public class OrdineArticolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdineArticoloPk pk;

	@ManyToOne
	@JoinColumn(name="id_ordine", insertable=false, updatable=false )
	private Ordine ordine;

	@ManyToOne
	@JoinColumn(name="id_articolo", insertable=false, updatable=false)
	private Articolo articolo;
	
	@Column(name="quantità")
	private Integer quantità;

	public OrdineArticolo() {
	}

	public void setPk(Integer idArt, Integer idOrd) {
		OrdineArticoloPk pk = new OrdineArticoloPk(idArt, idOrd);
		this.pk = pk;
	}

	public Integer getQuantità() {
		return quantità;
	}
	public void setQuantità(Integer quantità) {
		this.quantità=quantità;
	}

	public OrdineArticoloPk getPk() {
		return pk;
	}

	public void setPk(OrdineArticoloPk pk) {
		this.pk = pk;
	}

	@JsonIgnore
	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

}

