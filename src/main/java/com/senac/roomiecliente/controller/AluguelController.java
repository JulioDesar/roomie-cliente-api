package com.senac.roomiecliente.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.senac.roomiecliente.dto.AluguelDto;
import com.senac.roomiecliente.model.Aluguel;
import com.senac.roomiecliente.repository.AluguelRepository;
import com.senac.roomiecliente.repository.ClienteRepository;
import com.senac.roomiecliente.repository.ImovelRepository;

@RestController
@RequestMapping("/alugueis")
@CrossOrigin(origins = "http://localhost:3000")
public class AluguelController {

	@Autowired
	private AluguelRepository aluguelRepository;

	@Autowired
	private ClienteRepository clienteBd;

	@Autowired
	private ImovelRepository imovelBd;

	@GetMapping("/")
	public Page<Aluguel> allRents(
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<Aluguel> lista = aluguelRepository.findAll(paginacao);
		return lista;
	}

	@PostMapping("/")
	public ResponseEntity<Aluguel> save(@RequestBody AluguelDto aluguelForm, UriComponentsBuilder uriBuilder) {
		Aluguel aluguel = aluguelForm.convert(clienteBd, imovelBd);
		aluguelRepository.save(aluguel);

		URI uri = uriBuilder.path("/aluguel/{id}").buildAndExpand(aluguelForm.getId()).toUri();

		return ResponseEntity.created(uri)
				.body(new Aluguel(aluguel.getCliente(), aluguel.getImovel(), aluguelForm.getDias()));
	}

}
