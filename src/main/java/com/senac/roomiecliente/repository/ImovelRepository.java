package com.senac.roomiecliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.roomiecliente.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Integer>{

}
