package eu.winwinit.bcc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.repository.ArticoloRepository;
@Service

public class ArticoloServiceImpl implements ArticoloService {

	@Autowired
	private ArticoloRepository repository;

	@Override
	public List<Articolo> getAllArticoli() {
		return repository.findAll();
	}

	@Override
	public Articolo getArticoloById(Integer id) {
		if(repository.findById(id).isPresent())
			return repository.findById(id).get();
		else
			return null;
	}

	@Override
	public boolean updateArticolo(Articolo articolo) {
		return (repository.save(articolo) != null);
	}

	@Override
	public boolean deleteArticolo(Integer id) {
		repository.deleteById(id);
		return (!repository.findById(id).isPresent());
	}

	@Override
	public Articolo createArticolo(Articolo articolo) {
		return repository.save(articolo);
	}

}
