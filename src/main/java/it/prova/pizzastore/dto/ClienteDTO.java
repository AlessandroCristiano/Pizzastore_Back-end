package it.prova.pizzastore.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Cliente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;
	
	private Boolean attivo;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Long id, String nome, String cognome,
			String indirizzo, Boolean attivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	
	public Cliente buildClienteModel() {
		return new Cliente(this.id, this.nome, this.cognome, this.indirizzo, this.attivo);
	}
	
	public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel) {
		return new ClienteDTO(clienteModel.getId(), clienteModel.getNome(), clienteModel.getCognome(),
				clienteModel.getIndirizzo(), clienteModel.getAttivo());
	}
	
	public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput) {
		return modelListInput.stream().map(clienteEntity->{
			ClienteDTO result = ClienteDTO.buildClienteDTOFromModel(clienteEntity);
			return result;
		}).collect(Collectors.toList());
	}
	

}
