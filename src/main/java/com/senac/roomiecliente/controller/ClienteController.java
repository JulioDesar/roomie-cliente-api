package com.senac.roomiecliente.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.senac.roomiecliente.dto.ClienteDto;
import com.senac.roomiecliente.dto.ClienteUpdateDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.repository.ClienteRepository;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3030")
public class ClienteController {

	@Autowired
	private ClienteRepository bd;

	@GetMapping("/")
	public Page<Cliente> allClients(
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<Cliente> lista = bd.findAll(paginacao);
		return lista;
	}

	@PostMapping("/")
	public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteDto userForm, UriComponentsBuilder uriBuilder) {
		Cliente user = userForm.convert();
		bd.save(user);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).body(new ClienteDto(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Integer id,
			@RequestBody @Valid ClienteUpdateDto clienteUpdate) {
		Cliente cliente = clienteUpdate.atualizar(id, bd);

		return ResponseEntity.ok(new ClienteDto(cliente));
	}
}
