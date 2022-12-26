package it.prova.pizzastore.repository.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.prova.pizzastore.model.Cliente;

public class CustomPizzaRepositoryImpl implements CustomPizzaRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cliente> findByExample(Cliente example) {
		
	}

}
