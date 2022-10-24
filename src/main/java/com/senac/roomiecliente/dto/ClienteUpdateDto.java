package com.senac.roomiecliente.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.repository.ClienteRepository;

public class ClienteUpdateDto {

	@NotNull
	@NotEmpty
	private String telefone;

	@NotNull
	@NotEmpty
	private String cep;

	@NotNull
	@NotEmpty
	private String complemento;

	@NotNull
	@NotEmpty
	private String numeroCasa;

	@NotNull
	@NotEmpty
	private String senha;

	public ClienteUpdateDto() {
	}

	public ClienteUpdateDto(Cliente user) {
		this.telefone = user.getTelefone();
		this.cep = user.getCEP();
		this.complemento = user.getComplemento();
		this.numeroCasa = user.getNumeroCasa();
		this.senha = user.getSenha();
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public String getSenha() {
		return senha;
	}

	public Cliente atualizar(Integer id, ClienteRepository bd) {
		Cliente cliente = bd.getReferenceById(id);

		cliente.setTelefone(this.telefone);
		cliente.setCEP(this.cep);
		cliente.setComplemento(this.complemento);
		cliente.setNumeroCasa(this.numeroCasa);
		cliente.setSenha(new BCryptPasswordEncoder().encode(senha));

		bd.save(cliente);

		return cliente;
	}

}
