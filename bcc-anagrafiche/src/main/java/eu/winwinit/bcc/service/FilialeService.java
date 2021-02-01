package eu.winwinit.bcc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Filiale;
@Service
public interface FilialeService {

	public List<Filiale> findAll();
	
}
