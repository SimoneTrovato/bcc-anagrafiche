package eu.winwinit.bcc.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable

public class OrdineArticoloPk implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer idArticolo;
	private Integer idOrdine;
	
	public OrdineArticoloPk() {
		
	}

	public OrdineArticoloPk(Integer idArticolo, Integer idOrdine) {
		super();
		this.idArticolo = idArticolo;
		this.idOrdine = idOrdine;
	}
	
	public Integer getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Integer getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
