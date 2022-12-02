package com.senac.roomiecliente.dto;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.model.Imagem;
import com.senac.roomiecliente.model.Imovel;
import com.senac.roomiecliente.repository.ImagemRepository;

public class ImovelDto {

	@NotNull
	@NotEmpty
	private String titulo;

	@NotNull
	@NotEmpty
	private String cep;

	@NotNull
	@NotEmpty
	private String numero_casa;

	@NotNull
	@NotEmpty
	private String complemento;

	@NotNull
	@NotEmpty
	private String descricao;

	@NotNull
	@NotEmpty
	private String sexo;

	@NotNull
	@NotEmpty
	private String cidade;

	@NotNull
	@NotEmpty
	private String estado;

	@NotNull
	@NotEmpty
	private Integer numeroQuartos;

	@NotNull
	@NotEmpty
	private Double preco;

	private Integer cliente_id;

	public ImovelDto() {
	}

	public ImovelDto(Imovel imovel) {
		this.titulo = imovel.getTitulo();
		this.cep = imovel.getCep();
		this.numero_casa = imovel.getNumero_casa();
		this.complemento = imovel.getComplemento();
		this.descricao = imovel.getDescricao();
		this.sexo = imovel.getSexo();
		this.cidade = imovel.getCidade();
		this.estado = imovel.getEstado();
		this.numeroQuartos = imovel.getNumeroQuartos();
		this.cliente_id = imovel.getCliente().getId();
		this.preco = imovel.getPreco();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero_casa() {
		return numero_casa;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSexo() {
		return sexo;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public Double getPreco() {
		return preco;
	}

	public Integer getNumeroQuartos() {
		return numeroQuartos;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public Imovel converter(Cliente cliente, List<MultipartFile> imagens, ImagemRepository imagemBd)
			throws IOException {

		Imovel imovel = new Imovel(this.titulo, this.cep, this.numero_casa, this.complemento, this.descricao, this.sexo,
				this.cidade, this.estado, this.numeroQuartos, this.preco, cliente);

		for (MultipartFile imagem : imagens) {

			Imagem novaImagem = new Imagem(imagem.getBytes(), imovel);
			imovel.getImagens().add(novaImagem);
			imagemBd.save(novaImagem);
		}
		cliente.getImoveis().add(imovel);

		return imovel;
	}
}
