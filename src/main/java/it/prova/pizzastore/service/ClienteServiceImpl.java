package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;


@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listAll() {
		return (List<Cliente>) repository.findAll();
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente aggiorna(Cliente clienteInstance) {
		Cliente clienteReloaded = repository.findById(clienteInstance.getId()).orElse(null);
		
		if(clienteReloaded==null) {
			throw new NotFoundException("Elemento non trovato.");
		}
		
		clienteReloaded.setNome(clienteInstance.getNome());
		clienteReloaded.setCognome(clienteInstance.getCognome());
		clienteReloaded.setIndirizzo(clienteInstance.getIndirizzo());
		
		return repository.save(clienteReloaded);
	}

	@Override
	@Transactional
	public Cliente inserisciNuovo(Cliente clienteInstance) {
		clienteInstance.setAttivo(true);
		return repository.save(clienteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	@Override
	public List<Cliente> findByExample(Cliente example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional
	public void changeAbilitation(Long id) {
		Cliente clienteInstance = caricaSingoloElemento(id);
		if (clienteInstance == null)
			throw new NotFoundException("Elemento non trovato.");

		if (clienteInstance.getAttivo()) {
			clienteInstance.setAttivo(false);
		} else {
			clienteInstance.setAttivo(true);
		}
	}

}
