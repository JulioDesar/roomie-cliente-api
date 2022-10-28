package com.senac.roomiecliente.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "imovel_confirmar")
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

	@OneToMany(targetEntity = Imagem.class)
	@JoinColumn(name = "imagem_ibfk_1")
	private List<Imagem> imagens = new ArrayList<>();

	public Imovel() {
	}
	
	public Imovel(Integer id, String titulo, String cep, String numero_casa, String complemento, String descricao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cep = cep;
		this.numero_casa = numero_casa;
		this.complemento = complemento;
		this.descricao = descricao;
	}

	public Imovel(Integer id, String titulo, String cep, String numero_casa, String complemento, String descricao,
			Imagem imagem) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cep = cep;
		this.numero_casa = numero_casa;
		this.complemento = complemento;
		this.descricao = descricao;
		this.imagens.add(imagem);
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

	public List<Imagem> getImagens() {
		return imagens;
	}

}
