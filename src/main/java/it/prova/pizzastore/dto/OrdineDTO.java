package it.prova.pizzastore.dto;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {
	
	private Long id;
	
	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	
	private Integer costoTotale;
	
	private Boolean closed;
	
	@NotNull(message = "{cliente.notnull}")
	private ClienteDTO cliente;
	
	@NotNull(message = "{fattorino.notnull}")
	private UtenteDTO fattorino;
	
	@NotNull(message = "{pizze.notnull}")
	private Long[] pizzaIds;

	public OrdineDTO() {
		super();
	}

	public OrdineDTO(Long id, LocalDate data, String codice, Integer costoTotale, Boolean closed) {
		super();
		this.id = id;
		this.data = data;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.closed = closed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(Integer costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public UtenteDTO getFattorino() {
		return fattorino;
	}

	public void setFattorino(UtenteDTO fattorino) {
		this.fattorino = fattorino;
	}

	public Long[] getPizzaIds() {
		return pizzaIds;
	}

	public void setPizzaIds(Long[] pizzaIds) {
		this.pizzaIds = pizzaIds;
	}
	
	public Ordine buildOrdineModel() {
		Ordine result = new Ordine(this.id, this.data, this.codice, this.costoTotale, this.closed);
		
		if(pizzaIds!=null) {
			result.setPizze(Arrays.asList(pizzaIds).stream().map(id -> new Pizza(id)).collect(Collectors.toSet()));
		}
		
		if(this.fattorino != null) {
			result.setFattorino(this.fattorino.buildUtenteModel(false));
		}
		
		if(this.cliente != null) {
			result.setCliente(this.cliente.buildClienteModel());
		}
		return result;
	}
	
	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel) {
		OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.getCodice(),
				ordineModel.getCostoTotale(), ordineModel.getClosed());
		
		if(!ordineModel.getPizze().isEmpty()) {
			result.pizzaIds = ordineModel.getPizze().stream().map(id -> id.getId())
					.collect(Collectors.toList()).toArray(new Long[] {});
		}
		
		if(!ordineModel.getFattorino().equals(null)) {
			result.fattorino = UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino());
		}
		
		if(!ordineModel.getCliente().equals(null)) {
			result.cliente = ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente());
		}
		return result;
	}
	
	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput) {
		return modelListInput.stream().map(ordineEntity -> {
			OrdineDTO result = OrdineDTO.buildOrdineDTOFromModel(ordineEntity);
			return result;
		}).collect(Collectors.toList());
	}
	
}
