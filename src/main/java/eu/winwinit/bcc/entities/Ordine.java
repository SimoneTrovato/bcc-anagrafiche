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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ordini")

public class Ordine {

	private Integer id;
	//formato yyyy-mm-dd
	private Date data;
	private String cliente;

	public Ordine() {
	}

	public Ordine(Integer id, Date data, String cliente) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = true)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="data")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data=data;
	}
	
	@Column(name="cliente")
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente=cliente;
	}
	
	@OneToMany(mappedBy="ordine")
	@NotFound(action = NotFoundAction.IGNORE)
	 private List<OrdineArticolo> ordiniArticoli;
	
}
