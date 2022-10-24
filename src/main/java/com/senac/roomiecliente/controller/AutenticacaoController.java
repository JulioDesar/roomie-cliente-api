package com.senac.roomiecliente.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.roomiecliente.config.security.TokenService;
import com.senac.roomiecliente.dto.ClienteDto;
import com.senac.roomiecliente.dto.LoginDto;
import com.senac.roomiecliente.dto.TokenDto;
import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.repository.ClienteRepository;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ClienteRepository bd;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto form) {

		UsernamePasswordAuthenticationToken dadosLogin = form.Convert();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);

			Integer id = tokenService.getIdCliente(token);
			Optional<Cliente> user = bd.findById(id);
			ClienteDto usuario = new ClienteDto(user.get());

			if (user.isPresent()) {
				return ResponseEntity.ok(new TokenDto(token, "Bearer", usuario));
			}
			return ResponseEntity.notFound().build();

		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
