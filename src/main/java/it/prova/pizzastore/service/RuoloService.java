package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll();

	public Ruolo caricaSingoloElemento(Long id);

	public Ruolo aggiorna(Ruolo ruoloInstance);

	public Ruolo inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Long idToRemove);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);

}
