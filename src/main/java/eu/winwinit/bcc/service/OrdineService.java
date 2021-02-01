package eu.winwinit.bcc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.winwinit.bcc.model.OrdineRequest;
import eu.winwinit.bcc.model.OrdineResponse;
@Service

public interface OrdineService {

	public Integer creaOrdine(OrdineRequest ordineRequest);
		
	public List<OrdineResponse> getAllOrdini();
	
	public OrdineResponse getOrdineById(Integer id);

	public boolean uploadOrdine(OrdineRequest ordineRequest);
	
	public boolean deleteOrdine(Integer id);



	
}
