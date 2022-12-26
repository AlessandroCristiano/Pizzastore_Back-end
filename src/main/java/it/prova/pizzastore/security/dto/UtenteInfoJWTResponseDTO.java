package it.prova.pizzastore.security.dto;

public class UtenteInfoJWTResponseDTO {

	private String nome;
	private String cognome;
	private String type = "Bearer";
	private String username;
	private String role;

	public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, String ruolo) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.role = ruolo;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return role;
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
}