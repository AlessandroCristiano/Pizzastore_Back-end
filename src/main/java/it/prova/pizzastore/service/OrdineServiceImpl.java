package it.prova.pizzastore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.repository.ordine.OrdineRepository;
import it.prova.pizzastore.repository.utente.UtenteRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImpl implements OrdineService{
	
	@Autowired
	private OrdineRepository repository;
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public List<Ordine> listAll() {
		return (List<Ordine>) repository.findAll();
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ordine aggiorna(Ordine ordineInstance) {
		Ordine ordineReloaded = repository.findById(ordineInstance.getId()).orElse(null);
		
		if(ordineReloaded==null) {
			throw new NotFoundException("Elemento non trovato.");
		}
		
		ordineReloaded.setData(ordineInstance.getData());
		ordineReloaded.setCodice(ordineInstance.getCodice());
		ordineReloaded.setCostoTotale(ordineInstance.getCostoTotale());
		ordineReloaded.setCliente(ordineInstance.getCliente());
		ordineReloaded.setFattorino(ordineInstance.getFattorino());
		ordineReloaded.setPizze(ordineInstance.getPizze());
		
		return repository.save(ordineReloaded);
	}

	@Override
	@Transactional
	public Ordine inserisciNuovo(Ordine ordineInstance) {
		ordineInstance.setClosed(false);
		return repository.save(ordineInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional
	public void changeAbilitation(Long id) {
		Ordine ordineInstance = caricaSingoloElemento(id);
		if (ordineInstance == null)
			throw new NotFoundException("Elemento non trovato.");

		if (ordineInstance.getClosed()) {
			ordineInstance.setClosed(false);
		} else {
			ordineInstance.setClosed(true);
		}

	}

	@Override
	public Integer ricaviTotaliBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.calcolaRicaviTotaliTra(dataInizio, dataFine);
	}

	@Override
	public Integer ordiniTotaliBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.countByDataBetween(dataInizio, dataFine);
	}

	@Override
	public Integer pizzeOrdinateBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.countPizzeOrderedBetween(dataInizio, dataFine);
	}

	@Override
	public List<Cliente> clientiVirtuosiBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.findAllClientiVirtuosiBetween(dataInizio, dataFine);
	}

	@Override
	public List<Ordine> ordiniPerFattorino(String username) {
		Utente fattorino = utenteRepository.findByUsername(username).orElse(null);
		if (fattorino == null)
			throw new NotFoundException("Utente Not Found con username: " + username);

		return repository.findAllOrdiniApertiPerFattorino(fattorino.getId());
	}

}
