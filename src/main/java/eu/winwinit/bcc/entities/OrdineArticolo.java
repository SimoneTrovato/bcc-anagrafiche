package eu.winwinit.bcc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ordini_articoli")
public class OrdineArticolo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantità;

	public OrdineArticolo() {
	}

	public void setPk(Integer idArt, Integer idOrd) {
		OrdineArticoloPk pk = new OrdineArticoloPk(idArt, idOrd);
		this.pk = pk;
	}

	@Column(name="quantità")
	public Integer getQuantità() {
		return quantità;
	}
	public void setQuantità(Integer quantità) {
		this.quantità=quantità;
	}

	@EmbeddedId
	private OrdineArticoloPk pk;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@MapsId("idOrdine")
	@JoinColumn(name="id_ordine")
	private Ordine ordine;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@MapsId("idArticolo")
	@JoinColumn(name="id_articolo")
	private Articolo articolo;

	public OrdineArticoloPk getPk() {
		return pk;
	}

	public void setPk(OrdineArticoloPk pk) {
		this.pk = pk;
	}

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

