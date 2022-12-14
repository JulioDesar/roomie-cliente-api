package com.senac.roomiecliente.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.senac.roomiecliente.model.Cliente;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${api.jwt.expiration}")
	private String expiration;

	@Value("${api.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		Cliente logado = (Cliente) authentication.getPrincipal();
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API Roomie").setSubject(logado.getId().toString()).setIssuedAt(agora)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Integer getIdCliente(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(body.getSubject());
	}

}
