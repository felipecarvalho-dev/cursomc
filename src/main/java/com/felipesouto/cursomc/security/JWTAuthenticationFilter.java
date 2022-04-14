package com.felipesouto.cursomc.security;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipesouto.cursomc.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	/**
	 * AuthenticationManager é a principal interface de estratégia para
	 * autenticação. Se o principal da autenticação de entrada for válido e
	 * verificado, o metodo authenticate retorna uma instância de Authentication com
	 * o sinalizador de autenticado definido como verdadeiro. Do contrário, se o
	 * principal não for válido, ele lançará uma AuthenticationException. Para o
	 * último caso, ele retorna nulo se não puder decidir.
	 */
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res) throws AuthenticationException{
		

		/**
		 * Vamos pegar os valores passados na requisição POST para o endpoint /login,
		 * convertê-los em CredenciaisDTO instanciar um objeto do tipo
		 * UsernamePasswordAuthenticationToken passá-lo como parâmetro para o metodo
		 * authenticate que irá tentar realizar a autenticação. O framework fará isso
		 * usando os contratos implementados em UserDetails, UserDetailsService de forma
		 * automática
		 */
		try {
			/*
			 * getInputStream() Recupera o corpo da solicitação como dados binários usando
			 * um ServletInputStream. Este método ou getReader pode ser chamado para ler o
			 * corpo
			 */
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

			/*
			 * Criando esse objeto para passar para o metodo autenticate do
			 * authenticationManager verificar se o usuario e senha passados na requisição
			 * são válidos
			 */
			UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
					creds.getEmail(), creds.getSenha(), new ArrayList<>());

			/* Esse é o metodo que verifica se o usuario e senha passados são válidos */
			Authentication auth = authenticationManager.authenticate(authenToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth)throws IOException, ServletException{
		
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		res.addHeader("access-control-expose-headers", "Authorization");
		res.addHeader("Authorization", "Bearer " + token);
	
	
	}
	
	
	
}
