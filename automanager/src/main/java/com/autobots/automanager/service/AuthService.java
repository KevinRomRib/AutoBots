package com.autobots.automanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.autobots.automanager.repository.UsuarioRepositorio;

public class AuthService implements UserDetailsService{

	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		return repositorioUsuario.findByNome(nomeUsuario);
		
	}
}
