package eu.winwinit.bcc.model;

import java.util.List;

import eu.winwinit.bcc.entities.Ordine;


public class OrdineResponse {
	
	private Ordine ordine;
	private List<ArticoloQuantita> articoli;
	
	public OrdineResponse() {
	}

	public OrdineResponse(Ordine ordine, List<ArticoloQuantita> articoli) {
		super();
		this.ordine = ordine;
		this.articoli = articoli;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public List<ArticoloQuantita> getArticoli() {
		return articoli;
	}

	public void setArticoli(List<ArticoloQuantita> articoli) {
		this.articoli = articoli;
	}
	
	
	
	
	
	
	
	
	
}
