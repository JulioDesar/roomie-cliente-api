package com.senac.roomiecliente.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.senac.roomiecliente.config.validacao.Cpf;
import com.senac.roomiecliente.model.Cliente;

public class ClienteDto {

	private Integer id;

	@NotNull
	@NotEmpty
	@Cpf
	private String cpf;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String telefone;
	private LocalDate nascimento;


	private String cep;

	@NotNull
	@NotEmpty
	private String complemento;

	@NotNull
	@NotEmpty
	private String numeroCasa;

	@NotNull
	@NotEmpty
	private String sexo;
	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String senha;
	
	public ClienteDto() {
		
	}

	public ClienteDto(Cliente cliente) {

		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCPF().toString();
		this.telefone = cliente.getTelefone();
		this.nascimento = cliente.getNascimento();
		this.cep = cliente.getCEP();
		this.complemento = cliente.getComplemento();
		this.numeroCasa = cliente.getNumeroCasa();
		this.sexo = cliente.getSexo();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();

	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public String getCEP() {
		return cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Cliente convert() {

		return new Cliente(Long.parseLong(cpf), cep, nome, telefone, nascimento, email, senha, complemento, sexo, numeroCasa);
	}

}
