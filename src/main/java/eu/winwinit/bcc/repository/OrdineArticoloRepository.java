package eu.winwinit.bcc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;

public interface OrdineArticoloRepository extends JpaRepository<OrdineArticolo, Integer>{

	public List<OrdineArticolo> findByOrdine(Ordine ordine);
	
	public List<OrdineArticolo> findByArticolo(Articolo articolo);
	
}
