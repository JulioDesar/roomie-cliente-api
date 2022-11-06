package com.senac.roomiecliente.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.roomiecliente.dto.ImovelDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.model.Imagem;
import com.senac.roomiecliente.model.Imovel;
import com.senac.roomiecliente.repository.ClienteRepository;
import com.senac.roomiecliente.repository.ImagemRepository;
import com.senac.roomiecliente.repository.ImovelRepository;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private ImovelRepository imovelBd;
	
	@Autowired
	private ImagemRepository imagemBd;
	
	@Autowired
	private ClienteRepository clienteBd;

	@GetMapping("/imagem/{id}")
	public List<Imagem> exibirImagem(@PathVariable Integer id) {
		Imovel imovel = imovelBd.getReferenceById(id);
		return imovel.getImagens();
	}

	@PostMapping("/cadastrarImovel")
	public ResponseEntity<Imovel> saveImovel(@ModelAttribute("id") Integer idCliente, @ModelAttribute("data") String imovel,
			@ModelAttribute("file") @RequestParam("file") MultipartFile imagem) throws IOException {
		
		Cliente cliente = clienteBd.getReferenceById(idCliente);		
		ImovelDto imovelData = mapper.readValue(imovel, ImovelDto.class);
		Imovel imovelNovo = imovelData.converter(cliente, imagem, imagemBd);
		
		imovelBd.save(imovelNovo);

		return ResponseEntity.ok().build();
	}

}
