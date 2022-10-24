package com.senac.roomiecliente.dto;

public class TokenDto {

	private String token;
	private String tipo;
	private ClienteDto cliente;

	public TokenDto(String token, String tipo, ClienteDto user) {
		this.token = token;
		this.tipo = tipo;
		this.cliente = user;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public ClienteDto getUser() {
		return cliente;
	}

}
