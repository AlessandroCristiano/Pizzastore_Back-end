package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public Utente caricaSingoloUtenteConRuoli(Long id);

	public Utente aggiorna(Utente utenteInstance);

	public Utente inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idToRemove);

	public void changeUserAbilitation(Long utenteInstanceId);

	public Utente findByUsername(String username);
	
	public List<Utente> getAllFattorini();

}
