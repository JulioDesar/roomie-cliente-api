package com.senac.roomiecliente.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "cliente")
public class Cliente implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "CPF", unique = true)
	private Long CPF;

	@Column(name = "telefone")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	private LocalDate nascimento;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "cep", unique = true)
	private String CEP;

	@Column(name = "numero_casa")
	private String numeroCasa;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "senha")
	private String senha;

	@OneToMany()
	@JoinColumn(name = "id_cliente")
	@JsonBackReference
	private List<Imovel> imoveis = new ArrayList<>();

	@OneToMany()
	@JoinColumn(name = "id_cliente")
	@JsonBackReference
	private List<Aluguel> alugueis = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfis", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	List<Perfil> perfis = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(Long CPF, String CEP, String nome, String telefone, LocalDate nascimento, String email, String senha,
			String complemento, String sexo, String numeroCasa) {

		this.CPF = CPF;
		this.CEP = CEP;
		this.nome = nome;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.email = email;
		this.sexo = sexo;
		this.complemento = complemento;
		this.numeroCasa = numeroCasa;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCPF() {
		return CPF;
	}

	public void setCPF(Long cPF) {
		CPF = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cep) {
		CEP = cep;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CPF);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(CPF, other.CPF);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
