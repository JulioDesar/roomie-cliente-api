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

	private Integer id;

	@NotNull
	@NotEmpty
	private String cliente_id;

	@NotNull
	@NotEmpty
	private String imovel_id;

	@NotNull
	private Long dias;

	public AluguelDto() {

	}

	public AluguelDto(Aluguel aluguel) {
		this.id = aluguel.getId();
		this.cliente_id = aluguel.getCliente().getId().toString();
		this.imovel_id = aluguel.getImovel().getId().toString();
	}

	public Integer getId() {
		return id;
	}

	public String getCliente_id() {
		return cliente_id;
	}

	public String getImovel_id() {
		return imovel_id;
	}

	public Long getDias() {
		return dias;
	}

	public Aluguel convert(ClienteRepository cr, ImovelRepository imovelBd) {

		Optional<Cliente> cliente = cr.findById(Integer.parseInt(cliente_id));
		Optional<Imovel> imovel = imovelBd.findById(Integer.parseInt(imovel_id));

		return new Aluguel(cliente.get(), imovel.get(), this.dias);
	}

}
