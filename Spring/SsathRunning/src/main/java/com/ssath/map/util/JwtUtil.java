package com.ssath.map.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	
	//수정 필요
	private static final String SALT = "SSAth";
	
	//예외 처리, 파라미터 수정 필요
	public String createToken(String key, String value) {
		try {
			return Jwts.builder()
					.setHeaderParam("alg", "HS256")
					.setHeaderParam("typ", "JWT") //헤더완료
					.claim(key, value)
					.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) //서명완료
					.compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//토큰 유효성 검사 메서드
	public void valid(String token) {
		try {
			Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}