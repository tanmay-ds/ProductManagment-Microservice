package com.productmanagment.product.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.productmanagment.product.exceptionhandler.JwtSignatureException;
import com.productmanagment.product.security.CustomUserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtil {

	private String secretKey = "5hXIAvePisq/EgHwznfyQQ==";

	public Authentication getAuthentication(String token) {
		CustomUserDetail customUserDetail = new CustomUserDetail(extractUsername(token), extractEmail(token),
				extractAuthorities(token));
		return new UsernamePasswordAuthenticationToken(customUserDetail, token, extractAuthorities(token));

	}

	private Collection<? extends GrantedAuthority> extractAuthorities(String token) {
		return Arrays.stream(extractAllClaims(token).get("roles").toString().split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

	}

	public String extractEmail(String token) {
		return String.valueOf(extractAllClaims(token).get("email"));
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			throw new JwtSignatureException(e.getLocalizedMessage());
		}

	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

}