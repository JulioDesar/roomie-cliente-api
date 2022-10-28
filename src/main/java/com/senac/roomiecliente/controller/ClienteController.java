package com.senac.roomiecliente.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.senac.roomiecliente.dto.ClienteDto;
import com.senac.roomiecliente.dto.ClienteUpdateDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.model.Imagem;
import com.senac.roomiecliente.model.Imovel;
import com.senac.roomiecliente.repository.ClienteRepository;
import com.senac.roomiecliente.repository.ImovelRepository;

@RestController
@RequestMapping("/clients")
public class ClienteController {

	@Autowired
	private ClienteRepository bd;

	@Autowired
	private ImovelRepository ir;

	@GetMapping("/imagem/{id}")
	public List<Imagem> exibirImagem(@PathVariable Integer id) {
		Imovel imovel = ir.getReferenceById(id);
		return imovel.getImagens();
	}

	@PostMapping("/")
	public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteDto userForm, UriComponentsBuilder uriBuilder) {
		Cliente user = userForm.convert();
		bd.save(user);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).body(new ClienteDto(user));

	}

	@PostMapping("/cadastrarImovel")
	public ResponseEntity<Imovel> saveImovel(@Valid @RequestBody Imovel imovel, @RequestParam MultipartFile imagem) {

		try {
			imovel.getImagens().add(new Imagem(imovel.getId(), imagem.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

		ir.save(imovel);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Integer id,
			@RequestBody @Valid ClienteUpdateDto clienteUpdate) {
		Cliente cliente = clienteUpdate.atualizar(id, bd);

		return ResponseEntity.ok(new ClienteDto(cliente));

	}

}
