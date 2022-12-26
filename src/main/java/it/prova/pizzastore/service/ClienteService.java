package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();

	public Cliente caricaSingoloElemento(Long id);

	public Cliente aggiorna(Cliente clienteInstance);

	public Cliente inserisciNuovo(Cliente clienteInstance);

	public void rimuovi(Long idToRemove);
	
	public List<Cliente> findByExample(Cliente example);
	
	public void changeAbilitation(Long id);

}
