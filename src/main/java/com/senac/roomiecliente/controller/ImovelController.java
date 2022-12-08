package com.senac.roomiecliente.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.roomiecliente.dto.ImovelDto;
import com.senac.roomiecliente.dto.StatusDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.model.Imovel;
import com.senac.roomiecliente.repository.ClienteRepository;
import com.senac.roomiecliente.repository.ImagemRepository;
import com.senac.roomiecliente.repository.ImovelRepository;

@RestController
@RequestMapping("/imoveis")
@CrossOrigin(origins = "http://localhost:3000")
public class ImovelController {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private ImovelRepository imovelBd;

	@Autowired
	private ImagemRepository imagemBd;

	@Autowired
	private ClienteRepository clienteBd;

	@GetMapping("/imovel")
	public Page<Imovel> todosImoveis(
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 8) Pageable paginacao) {

		Pageable pagina = paginacao;
		Page<Imovel> lista = imovelBd.findAll(pagina);

		return lista;

	}

	@RequestMapping(value = "/cadastrarImovel", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = {
			"multipart/mixed", MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Imovel> saveImovel(@RequestParam(value = "id", required = false) Integer idCliente,
			@ModelAttribute("data") String imovel,
			@RequestParam(value = "file", required = false) List<MultipartFile> imagem) throws IOException {

		Cliente cliente = clienteBd.getReferenceById(idCliente);
		ImovelDto imovelData = mapper.readValue(imovel, ImovelDto.class);
		Imovel imovelNovo = imovelData.converter(cliente, imagem, imagemBd);

		imovelBd.save(imovelNovo);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/imovel/{id}")
	public ResponseEntity<Imovel> updateStatusImovel(@PathVariable Integer id, @RequestBody StatusDto status) {

		Optional<Imovel> imovel = imovelBd.findById(id);
		Imovel novoImovel = imovel.get();
		novoImovel.setStatus(status.getStatus());

		imovelBd.save(novoImovel);

		return ResponseEntity.ok().build();
	}

}
