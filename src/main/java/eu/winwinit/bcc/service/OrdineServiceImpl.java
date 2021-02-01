package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;
import eu.winwinit.bcc.model.ArticoloQuantita;
import eu.winwinit.bcc.model.DettaglioArticolo;
import eu.winwinit.bcc.model.OrdineRequest;
import eu.winwinit.bcc.model.OrdineResponse;
import eu.winwinit.bcc.repository.ArticoloRepository;
import eu.winwinit.bcc.repository.OrdineArticoloRepository;
import eu.winwinit.bcc.repository.OrdineRepository;
@Service
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository ordineRepo;
	@Autowired
	private OrdineArticoloRepository ordineArtRepo;
	@Autowired
	private ArticoloRepository articoloRepo;

	private  List<ArticoloQuantita> setLista(List<OrdineArticolo> articoli , List<ArticoloQuantita> articoliQList) {
		
		for(OrdineArticolo ordiniArt : articoli) {
			ArticoloQuantita artQ = new ArticoloQuantita();
			artQ.setIdArticolo(ordiniArt.getArticolo().getId());
			artQ.setCodice(ordiniArt.getArticolo().getCodice());
			artQ.setNome(ordiniArt.getArticolo().getNome());
			artQ.setQuantità(ordiniArt.getQuantità());
			articoliQList.add(artQ);
		}
		
		return articoliQList;
		
	}
	
	
	@Override
	public Integer creaOrdine(OrdineRequest ordineRequest) {
		Ordine ordine = new Ordine();
		ordine.setCliente(ordineRequest.getCliente());
		ordine.setData(ordineRequest.getData());
		ordine = ordineRepo.save(ordine);
		//salviamo i dettagli degli articoli
		for(DettaglioArticolo dettaglio : ordineRequest.getDettaglioArticoli()) {
			OrdineArticolo ordineArt = new OrdineArticolo();
			ordineArt.setQuantità(dettaglio.getQuantità());
			ordineArt.setPk(dettaglio.getIdArticolo(), ordine.getId());
			ordineArt.setOrdine(ordine);
			ordineArt.setArticolo(articoloRepo.getOne(dettaglio.getIdArticolo()) );
			ordineArtRepo.save(ordineArt);
		}
		return ordine.getId();
	}

	@Override
	public List<OrdineResponse> getAllOrdini(){
		List<OrdineResponse> listaOrdResp  = new ArrayList<>();
		//creo la lista di tutti gli ordine nel repo
		List<Ordine> listaOrdini = new ArrayList<>();	
		listaOrdini = ordineRepo.findAll();
		//scorro lista per inserire gli id dentro articoli 
		for(Ordine ordini : listaOrdini) {
			List<OrdineArticolo> articoli = ordineArtRepo.findByOrdine(ordini);
			OrdineResponse response = new OrdineResponse();
			List<ArticoloQuantita> articoliQList = new ArrayList<>();
			
			response.setOrdine(ordini);
			response.setArticoli(setLista(articoli, articoliQList));
			listaOrdResp.add(response);
		}
		return listaOrdResp;
	}

	@Override
	public OrdineResponse getOrdineById(Integer idOrdine){
		OrdineResponse response = new OrdineResponse();
		if(ordineRepo.findById(idOrdine).isPresent()) {
			Ordine ordine = ordineRepo.findById(idOrdine).get();
			List<OrdineArticolo> articoli = ordineArtRepo.findByOrdine(ordine);
			List<ArticoloQuantita> articoliQList = new ArrayList<>();
			response.setOrdine(ordine);
			response.setArticoli(setLista(articoli, articoliQList));
		}
		return response;
	}

	@Override
	public boolean deleteOrdine(Integer idOrdine) {

		ordineRepo.deleteById(idOrdine);
		return (!ordineRepo.findById(idOrdine).isPresent());
	}

	@Override
	public boolean uploadOrdine(OrdineRequest ordineRequest) {

		Ordine ordine = new Ordine();
		ordine.setCliente(ordineRequest.getCliente());
		ordine.setData(ordineRequest.getData());
		ordine.setId(ordineRequest.getId());
		ordine = ordineRepo.save(ordine);

		for(DettaglioArticolo dettaglio : ordineRequest.getDettaglioArticoli()) {
			OrdineArticolo ordineArt = new OrdineArticolo();
			ordineArt.setQuantità(dettaglio.getQuantità());
			ordineArt.setPk(dettaglio.getIdArticolo(), ordine.getId());
			ordineArt.setOrdine(ordine);
			ordineArt.setArticolo(articoloRepo.getOne(dettaglio.getIdArticolo()) );
			ordineArtRepo.save(ordineArt);
		}
		return (ordineRepo.save(ordine) != null);
	}}
