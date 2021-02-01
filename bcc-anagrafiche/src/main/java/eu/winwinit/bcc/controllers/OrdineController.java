package eu.winwinit.bcc.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.winwinit.bcc.model.OrdineRequest;
import eu.winwinit.bcc.model.OrdineResponse;
import eu.winwinit.bcc.service.OrdineService;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/ordine")

public class OrdineController {

	@Autowired
	private OrdineService ordineService;

	@PostMapping(value="/create", consumes = "application/json")
	public Integer create(@RequestBody OrdineRequest ordine) {
		return ordineService.creaOrdine(ordine);
	}

	@GetMapping(value="read")
	public List<OrdineResponse> findAll() {
		return ordineService.getAllOrdini();
	}

	@GetMapping(path = "read/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {
		if(ordineService.getOrdineById(id).getOrdine() != null) {
			return new ResponseEntity<>(ordineService.getOrdineById(id),HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Ordine non esistente", HttpStatus.BAD_REQUEST);

	}
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id)  {
		if(ordineService.getOrdineById(id).getOrdine() !=null) {
		if(ordineService.deleteOrdine(id)) {
			return new ResponseEntity<>("Ordine eliminato con successo", HttpStatus.OK);
		}
		else
    		return new ResponseEntity<>("Impossibile eliminare l'ordine con id "+id, HttpStatus.OK);
		}else
        return new ResponseEntity<>("Ordine non esistente", HttpStatus.BAD_REQUEST);

	}

	@PutMapping(path = "/update")
	public ResponseEntity<String> update( @RequestBody OrdineRequest ordineRequest) throws BadHttpRequest {
		if (ordineService.getOrdineById(ordineRequest.getId()) != null) {  
			if(ordineService.uploadOrdine(ordineRequest)) {
				return new ResponseEntity<>("Ordine aggiornato con successo", HttpStatus.OK);
			} else
				return new ResponseEntity<>("Impossibile aggiornare l'ordine con id "+ordineRequest.getId(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Ordine non esistente", HttpStatus.BAD_REQUEST);
	}
} 