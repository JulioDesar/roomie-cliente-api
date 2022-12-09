package com.senac.roomiecliente.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "aluguel")
public class Aluguel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_imovel", nullable = false)
	private Imovel imovel;

	@Column(name = "data_inicial")
	private LocalDate dataInicial = LocalDate.now();

	@Column(name = "data_final")
	private LocalDate dataFinal;

	public Aluguel() {

	}

	public Aluguel(Cliente cliente, Imovel imovel, Long dias) {
		this.cliente = cliente;
		this.imovel = imovel;
		this.imovel.setStatus(Status.ALUGADO);
		this.dataFinal = dataInicial.plusDays(dias);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluguel other = (Aluguel) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

}
