package com.senac.roomiecliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.roomiecliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByEmail(String username);

}
