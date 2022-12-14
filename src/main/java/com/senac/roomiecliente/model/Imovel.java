package com.senac.roomiecliente.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity()
@Table(name = "imovel")
public class Imovel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "cep")
	private String cep;

	@Column(name = "numero_casa")
	private String numero_casa;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "aprovado")
	@Enumerated(EnumType.STRING)
	private Status status = Status.EM_ANALISE;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "quantidade_quarto")
	private Integer numeroQuartos;

	@Column(name = "preco")
	private Double preco;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente", nullable = false)
	@JsonBackReference
	private Cliente cliente;

	@OneToOne(mappedBy = "imovel", cascade = CascadeType.ALL)
	@JoinColumn(name = "id_imovel", nullable = false)
	@JsonBackReference
	private Aluguel aluguel;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_imovel")
	@JsonManagedReference
	private List<Imagem> imagens = new ArrayList<>();

	public Imovel() {
	}

	public Imovel(String titulo, String cep, String numero_casa, String complemento, String descricao, String sexo,
			String cidade, String estado, Integer numeroQuartos, Double preco, Cliente cliente) {
		this.titulo = titulo;
		this.cep = cep;
		this.numero_casa = numero_casa;
		this.complemento = complemento;
		this.descricao = descricao;
		this.sexo = sexo;
		this.cidade = cidade;
		this.estado = estado;
		this.numeroQuartos = numeroQuartos;
		this.cliente = cliente;
		this.preco = preco;
	}

	public Imovel(String titulo, String cep, String numero_casa, String complemento, String descricao, String sexo,
			String cidade, String estado, Integer numeroQuartos, Double preco, Cliente cliente, Aluguel aluguel) {
		this.titulo = titulo;
		this.cep = cep;
		this.numero_casa = numero_casa;
		this.complemento = complemento;
		this.descricao = descricao;
		this.sexo = sexo;
		this.cidade = cidade;
		this.estado = estado;
		this.numeroQuartos = numeroQuartos;
		this.cliente = cliente;
		this.preco = preco;
		this.aluguel = aluguel;
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
		Imovel other = (Imovel) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero_casa() {
		return numero_casa;
	}

	public void setNumero_casa(String numero_casa) {
		this.numero_casa = numero_casa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumeroQuartos() {
		return numeroQuartos;
	}

	public void setNumeroQuartos(Integer numeroQuartos) {
		this.numeroQuartos = numeroQuartos;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

}
