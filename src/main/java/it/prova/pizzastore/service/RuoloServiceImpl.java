package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.repository.ruolo.RuoloRepository;


@Service
@Transactional(readOnly = true)
public class RuoloServiceImpl implements RuoloService{

	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ruolo aggiorna(Ruolo ruoloInstance) {
		return null;

	}

	@Override
	@Transactional
	public Ruolo inserisciNuovo(Ruolo ruoloInstance) {
		return ruoloRepository.save(ruoloInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

}
