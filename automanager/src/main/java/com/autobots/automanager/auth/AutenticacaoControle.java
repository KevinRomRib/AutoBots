package com.autobots.automanager.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.record.Login;
import com.autobots.automanager.service.TokenService;

@RestController
public class AutenticacaoControle{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/usuario/login")
	public String usuarioLogin(@RequestBody Login login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.nomeUsuario(), login.senha());
		
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
		var usuario = (User) authenticate.getPrincipal();
		
		return tokenService.gerarToken(usuario);
	}

	

}
