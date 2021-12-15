package com.rest.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.dao.CompteRepository;
import com.rest.dao.OperationRepository;
import com.rest.entity.Compte;
import com.rest.entity.Operation;

@RestController
@RequestMapping("compte")
public class CompteService {
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	
	@GetMapping({"/comptes"})
	public List<Compte> getComptes(){
		return compteRepository.findAll();
	}
	
	@GetMapping(path="/compte/{id}")
	public Compte getCompte(@PathVariable("id") long id) {
		return compteRepository.findById(id).get();
	}
	
	@PostMapping(path="/compte/add", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Compte addCompte(@RequestBody Compte compte) {
		return compteRepository.save(compte);
	}
	
	@PutMapping(path="/compte/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Compte updateCompte(@RequestBody Compte c, @PathVariable("code") long code) {
		long id = c.getCode();
		double solde = c.getSolde();
		Date d = c.getDateCreation();
		Compte cp = compteRepository.findById(code).get();
		cp.setCode(id);
		cp.setDateCreation(d);
		cp.setSolde(solde);
		return compteRepository.save(cp);
	}
	
	@DeleteMapping("/compte/{id}")
	public void deleteCompte(@PathVariable("code") long code) {
		compteRepository.deleteById(code);
	}
	
	@GetMapping("/operations/{code}")
	public List<Operation> getOperations(@PathVariable("code") long code){
		return operationRepository.findBycode(code);
	}
	
	@GetMapping("/operations")
	public List<Operation> getOperations(){
		return operationRepository.findAll();
	}
	
	@PutMapping("/retrait/{valeur}/{id}/")
	public Compte Retrait(@PathVariable long id, @PathVariable("valeur") Double somme)
	{
		Compte compte= compteRepository.findById(id).get();
		Double res = compte.getSolde()-somme;
		compte.setSolde(res);
		return compteRepository.save(compte);
	}
	
	@PutMapping("/versement/{valeur}/{id}/")
	public Compte Versement(@PathVariable("id") long id,  @PathVariable("valeur") Double somme)
	{
		Compte compte= compteRepository.findById(id).get();
		Double res = compte.getSolde()+somme;
		compte.setSolde(res);
		return compteRepository.save(compte);
	}

	@PutMapping("/virement/{valeur}/{idRetrait}/{idVersement}/")
	@Secured("USER")
	public Compte Virement(@PathVariable("idRetrait") long idRetrait, @PathVariable("idVersement") long idVersement, @PathVariable("valeur") Double somme)
	{
		Compte compte= compteRepository.findById(idRetrait).get();
		Compte compte1= compteRepository.findById(idVersement).get();
		Double res = compte.getSolde()-somme;
		Double res2 = compte1.getSolde()+somme;
		compte1.setSolde(res2);
		compte.setSolde(res);
		compteRepository.save(compte);
		return compteRepository.save(compte1);
	}

}
