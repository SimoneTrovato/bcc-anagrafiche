package eu.winwinit.bcc.entities;



import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ordini")
public class Ordine {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = true)
	private Integer id;

	@Column(name="data")
	private Date data;
	
	@Column(name="cliente")
	private String cliente;
	
	@OneToMany(mappedBy="ordine")
	private List<OrdineArticolo> ordiniArticoli;

	public Ordine() {
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data=data;
	}


	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente=cliente;
	}

	public void setOrdiniArticoli(List<OrdineArticolo> ordiniArticoli) {
		this.ordiniArticoli = ordiniArticoli;
	}

	@JsonIgnore
	public List<OrdineArticolo> getOrdiniArticoli() {
		return ordiniArticoli;
	}

}
