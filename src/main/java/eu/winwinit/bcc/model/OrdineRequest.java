package eu.winwinit.bcc.model;

import java.sql.Date;
import java.util.List;

public class OrdineRequest {
	private Integer id;
	private Date data;
	private String cliente;
	private List<DettaglioArticolo> dettaglioArticoli;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public List<DettaglioArticolo> getDettaglioArticoli() {
		return dettaglioArticoli;
	}
	public void setDettaglioArticoli(List<DettaglioArticolo> dettaglioArticoli) {
		this.dettaglioArticoli = dettaglioArticoli;
	}
	
	
	}
