package it.prova.pizzastore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.PizzaDTO;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.PizzaService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public List<PizzaDTO> listAll() {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAll());
	}
	
	@GetMapping("/{id}")
	public PizzaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Pizza pizza = pizzaService.caricaSingoloElemento(id);

		if (pizza == null)
			throw new NotFoundException("Pizza not found con id: " + id);

		return PizzaDTO.buildPizzaDTOFromModel(pizza);
	}
	
	@PostMapping
	public PizzaDTO createNew(@Valid @RequestBody PizzaDTO pizzaInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (pizzaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Pizza pizzaInserita = pizzaService.inserisciNuovo(pizzaInput.buildPizzaModel());
		return PizzaDTO.buildPizzaDTOFromModel(pizzaInserita);
	}
	
	@PutMapping("/{id}")
	public PizzaDTO update(@Valid @RequestBody PizzaDTO pizzaInput, @PathVariable(required = true) Long id) {
		Pizza pizza = pizzaService.caricaSingoloElemento(id);
		
		if(pizza==null) {
			throw new NotFoundException("Pizza not found con id: " + id);
		}
		pizzaInput.setId(id);
		Pizza pizzaAggiornata = pizzaService.aggiorna(pizzaInput.buildPizzaModel());
		return PizzaDTO.buildPizzaDTOFromModel(pizzaAggiornata);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true)Long id) {
		pizzaService.rimuovi(id);
	}
	
	@PostMapping("/search")
	public List<PizzaDTO> search(@RequestBody PizzaDTO example) {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.findByExample(example.buildPizzaModel()));
	}
	
	@GetMapping("changeAbilitation/{id}")
	public void changeAbilitation(@PathVariable(value= "id", required = true) long id) {
		pizzaService.changeAbilitation(id);
	}

}
