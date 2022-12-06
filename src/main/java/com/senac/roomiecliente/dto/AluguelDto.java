package com.senac.roomiecliente.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.senac.roomiecliente.model.Aluguel;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.model.Imovel;
import com.senac.roomiecliente.repository.ClienteRepository;
import com.senac.roomiecliente.repository.ImovelRepository;

public class AluguelDto {

	@NotNull
	@NotEmpty
	private Integer id;

	@NotNull
	@NotEmpty
	private Integer cliente_id;

	@NotNull
	@NotEmpty
	private Integer imovel_id;

	@NotNull
	@NotEmpty
	private Long dias;

	public AluguelDto() {

	}

	public AluguelDto(Aluguel aluguel) {
		this.id = aluguel.getId();
		this.cliente_id = aluguel.getCliente().getId();
		this.imovel_id = aluguel.getImovel().getId();
	}

	public Integer getId() {
		return id;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public Integer getImovel_id() {
		return imovel_id;
	}

	public Long getDias() {
		return dias;
	}

	public Aluguel convert(ClienteRepository cr, ImovelRepository imovelBd) {

		Optional<Cliente> cliente = cr.findById(cliente_id);
		Optional<Imovel> imovel = imovelBd.findById(imovel_id);

		return new Aluguel(cliente.get(), imovel.get(), this.dias);
	}

}
