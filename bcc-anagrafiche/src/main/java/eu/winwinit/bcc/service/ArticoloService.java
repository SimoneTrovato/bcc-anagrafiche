package eu.winwinit.bcc.service;

import java.util.List;



import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
@Service
public interface ArticoloService {

	public List<Articolo> getAllArticoli();

	public Articolo getArticoloById(Integer id);

	public boolean updateArticolo(Articolo articolo);

	public boolean deleteArticolo(Integer id);

	public Articolo createArticolo(Articolo articolo);

}
