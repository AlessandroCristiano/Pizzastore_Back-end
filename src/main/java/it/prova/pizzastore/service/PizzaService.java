package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Pizza;

public interface PizzaService {
	
	public List<Pizza> listAll();

	public Pizza caricaSingoloElemento(Long id);

	public Pizza aggiorna(Pizza pizzaInstance);

	public Pizza inserisciNuovo(Pizza pizzaInstance);

	public void rimuovi(Long idToRemove);
	
	public List<Pizza> findByExample(Pizza example);
	
	public void changeAbilitation(Long id);

}
