package com.example.ISA2020.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ISA2020.security.TokenUtils;


/// Sprecava sve zahteve ka serveru osim onih koji su definisani u WebSecurityConfig klasi
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private TokenUtils tokenUtils;

  private UserDetailsService userDetailsService;

  public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
      this.tokenUtils = tokenHelper;
      this.userDetailsService = userDetailsService;
  }

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws IOException, ServletException {

      String username;
      String authToken = tokenUtils.getToken(request);

      if (authToken != null) {
          username = tokenUtils.getUsernameFromToken(authToken);

          if (username != null) {
              UserDetails userDetails;
              try {
                  userDetails = userDetailsService.loadUserByUsername(username);
                  if (tokenUtils.validateToken(authToken, userDetails)) {
                      TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                      authentication.setToken(authToken);
                      SecurityContextHolder.getContext().setAuthentication(authentication);
                  }
              } catch (UsernameNotFoundException e) {
              }
          }
      }

      chain.doFilter(request, response);
  }

}
