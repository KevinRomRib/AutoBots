package com.autobots.automanager.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.autobots.automanager.entity.User;

public class TokenService {

	   public String gerarToken(User user) {
	        return JWT.create()
	                .withIssuer("")
	                .withSubject(user.getUsername())
	                .withClaim("id", user.getId())
	                .withExpiresAt(LocalDateTime.now()
	                        .plusMinutes(60)
	                        .toInstant(ZoneOffset.of("-03:00"))).sign(Algorithm.HMAC256("chaveToken"));
	    }


	    public String getSubject(String token) {
	        return JWT.require(Algorithm.HMAC256("chaveToken"))
	                .withIssuer("")
	                .build().verify(token).getSubject();

	    }
}
