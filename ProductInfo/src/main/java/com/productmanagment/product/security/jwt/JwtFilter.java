package com.productmanagment.product.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productmanagment.product.exceptionhandler.JwtSignatureException;
import com.productmanagment.product.model.ResponseModel;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(jwt);
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(jwt));
			}
			filterChain.doFilter(request, response);

		} catch (ExpiredJwtException | AccessDeniedException | JwtSignatureException e) {

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			ObjectMapper mapper = new ObjectMapper();
			ResponseModel responseMessage = new ResponseModel(new Date().toString(), HttpStatus.UNAUTHORIZED,
					e.getLocalizedMessage());
			out.print(mapper.writeValueAsString(responseMessage));
			out.flush();
		}

	}

}