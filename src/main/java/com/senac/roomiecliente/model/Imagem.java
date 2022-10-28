package com.senac.roomiecliente.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "imagem")
public class Imagem {

	@Id
	@Column(name = "id")
	private Integer id;

	@Lob
	@ManyToOne(targetEntity = Imovel.class)
	@JoinColumn(name = "imagem_ibfk_1")
	private byte[] imagem;

	public Imagem() {
	}

	public Imagem(Integer id, byte[] imagem) {
		this.id = id;
		this.imagem = imagem;
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

}
