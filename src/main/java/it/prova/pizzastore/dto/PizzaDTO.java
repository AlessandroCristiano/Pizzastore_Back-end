package it.prova.pizzastore.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Pizza;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PizzaDTO {
	
	private Long id;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	
	@NotBlank(message = "{ingredienti.notblank}")
	private String ingredienti;
	
	@NotNull(message = "{prezzobase.notblank}")
	private Integer prezzoBase;
	
	private Boolean attivo;

	public PizzaDTO() {
		super();
	}

	public PizzaDTO(Long id, String descrizione, String ingredienti, Integer prezzoBase,
			Boolean attivo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Integer getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(Integer prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	
	public Pizza buildPizzaModel() {
		return new Pizza(this.id, this.descrizione, this.ingredienti, this.prezzoBase, this.attivo);
	}
	
	public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
		return new PizzaDTO(pizzaModel.getId(), pizzaModel.getDescrizione(), pizzaModel.getIngredienti(),
				pizzaModel.getPrezzoBase(), pizzaModel.getAttivo());
	}
	
	public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput) {
		return modelListInput.stream().map(pizzaEntity -> {
			PizzaDTO result = PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
			return result;
		}).collect(Collectors.toList());
	}

	
}
