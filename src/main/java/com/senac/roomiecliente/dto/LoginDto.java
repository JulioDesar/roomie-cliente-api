package com.senac.roomiecliente.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

	@Email
	private String email;
	@NotEmpty
	private String senha;

	public LoginDto() {
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken Convert() {

		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
