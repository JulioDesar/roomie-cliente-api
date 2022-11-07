package com.senac.roomiecliente.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity()
@Table(name = "imagem")
public class Imagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	@Column(name = "imagem", columnDefinition = "BLOB")
	private byte[] imagem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_imovel", nullable = false)
	@JsonBackReference
	private Imovel imovel;

	public Imagem() {
	}

	public Imagem(byte[] imagem, Imovel imovel) {
		this.imagem = imagem;
		this.imovel = imovel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imagem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		return Objects.equals(imagem, other.imagem);
	}
	
	public byte[] getImagem() {
		return imagem;
	}
	
	public Imovel getImovel() {
		return imovel;
	}

}
