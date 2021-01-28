package eu.winwinit.bcc.controllers;

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

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.service.ArticoloService;
import javassist.tools.web.BadHttpRequest;
@RestController
@RequestMapping(path = "/articolo")
public class ArticoloController {

   @Autowired
    private ArticoloService service;
    
    @GetMapping(value="read")
    public Iterable<Articolo> findAll() {
        return service.getAllArticoli();
    }
    
    @GetMapping(path = "read/{id}")
    public Articolo find(@PathVariable("id") Integer id) {
      return service.getArticoloById(id);
    }
    
    @PostMapping(value="/create", consumes = "application/json")
    public Articolo create(@RequestBody Articolo articolo) {
        return service.createArticolo(articolo);
    }
    
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
    	service.deleteArticolo(id);
    }
    
    @PutMapping(path = "/upload")
    public ResponseEntity<String> update(@RequestBody Articolo articolo) throws BadHttpRequest {
        if (service.getArticoloById(articolo.getId()) != null) {  
        	if(service.updateArticolo(articolo)) {
        		return new ResponseEntity<>("Articolo aggiornato con successo", HttpStatus.OK);
        	} else
        		return new ResponseEntity<>("Impossibile aggiornare l'Articolo con id "+articolo.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Articolo non esistente", HttpStatus.BAD_REQUEST);
    }
    
}
