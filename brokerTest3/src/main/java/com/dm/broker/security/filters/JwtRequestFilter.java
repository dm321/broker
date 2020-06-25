package com.dm.broker.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dm.broker.security.MyUserDetails;
import com.dm.broker.security.MyUserDetailsService;
import com.dm.broker.security.jwt.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		
		String email = null;
		String jwt = null;
		
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer "))
		{
			jwt = authorizationHeader.substring(7);
			email = jwtUtil.extractUsername(jwt);
		}
			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				MyUserDetails myUserDetails = this.myUserDetailsService.loadUserByUsername(email);
				
				if( jwtUtil.validateToken(jwt, myUserDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
							(myUserDetails, null, myUserDetails.getAuthorities());
					usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		}
		
	}


