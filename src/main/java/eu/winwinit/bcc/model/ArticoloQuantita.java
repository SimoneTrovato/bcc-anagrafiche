package eu.winwinit.bcc.model;

public class ArticoloQuantita {

	private Integer idArticolo;
	private String nome;
	private Integer codice;
	private Integer quantità;
	
	public ArticoloQuantita() {
		
	}

	public ArticoloQuantita(Integer idArticolo, String nome, Integer codice, Integer quantità) {
		super();
		this.idArticolo = idArticolo;
		this.nome = nome;
		this.codice = codice;
		this.quantità = quantità;
	}

	public Integer getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
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

	public Integer getQuantità() {
		return quantità;
	}

	public void setQuantità(Integer quantità) {
		this.quantità = quantità;
	}
	
	
}
