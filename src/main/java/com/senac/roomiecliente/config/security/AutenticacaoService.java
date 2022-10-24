package com.senac.roomiecliente.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.senac.roomiecliente.model.Cliente;
import com.senac.roomiecliente.repository.ClienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private ClienteRepository bd;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> usuario = bd.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Cliente não encontrado!");
	}
}