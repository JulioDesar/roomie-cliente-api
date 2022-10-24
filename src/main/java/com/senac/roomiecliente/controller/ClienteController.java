package com.senac.roomiecliente.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.senac.roomiecliente.dto.ClienteDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.repository.ClienteRepository;

@RestController
@RequestMapping("/clients")
public class ClienteController {
	
	@Autowired
    private ClienteRepository bd;
	
	@PostMapping("/")
	public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteDto userForm,
			UriComponentsBuilder uriBuilder) {
		Cliente user = userForm.convert();
		bd.save(user);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).body(new ClienteDto(user));

	}

}
