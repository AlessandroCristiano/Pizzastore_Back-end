package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;


@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private PizzaRepository repository;

	@Override
	public List<Pizza> listAll() {
		return (List<Pizza>) repository.findAll();
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pizza aggiorna(Pizza pizzaInstance) {
		Pizza pizzaReloaded = repository.findById(pizzaInstance.getId()).orElse(null);
		if (pizzaReloaded == null)
			throw new NotFoundException("Elemento non trovato");

		pizzaReloaded.setDescrizione(pizzaInstance.getDescrizione());
		pizzaReloaded.setIngredienti(pizzaInstance.getIngredienti());
		pizzaReloaded.setPrezzoBase(pizzaInstance.getPrezzoBase());

		return repository.save(pizzaReloaded);
	}

	@Override
	@Transactional
	public Pizza inserisciNuovo(Pizza pizzaInstance) {
		pizzaInstance.setAttivo(true);
		return repository.save(pizzaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
		
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional
	public void changeAbilitation(Long id) {
		Pizza pizzaInstance = caricaSingoloElemento(id);
		if (pizzaInstance == null)
			throw new NotFoundException("Elemento non trovato.");

		if (pizzaInstance.getAttivo()) {
			pizzaInstance.setAttivo(false);
		} else {
			pizzaInstance.setAttivo(true);
		}
		
	}

}
