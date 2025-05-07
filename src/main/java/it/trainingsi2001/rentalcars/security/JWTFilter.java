package it.trainingsi2001.rentalcars.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@Component
public class JWTFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			if (StringUtils.isBlank(jwt)) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
			} else {
				try {
					String username = jwtUtil.validateTokenAndRetrieveSubject(jwt);

					UserDetails userDetails = userDetailsService.loadUserByUsername(username);

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);

				} catch (TokenExpiredException exc) {
					LOGGER.error("JWT token is expired: {}", exc.getMessage());
					request.setAttribute("expired", exc.getMessage());
				} catch (JWTVerificationException exc) {
					LOGGER.error("Cannot set user authentication: {}", exc);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}