package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll();

	public Ordine caricaSingoloElemento(Long id);

	public Ordine aggiorna(Ordine ordineInstance);

	public Ordine inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);
	
	public List<Ordine> findByExample(Ordine example);

}
