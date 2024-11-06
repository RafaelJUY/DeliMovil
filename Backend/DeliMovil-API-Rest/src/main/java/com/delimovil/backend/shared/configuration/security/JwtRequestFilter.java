package com.delimovil.backend.shared.configuration.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Clase S5
// Clase interceptadora de cada petición HTTP. Verifica que se haya enviado un token, que sea valido y guarda la informacion del usuario que hizo la petición.
//@Profile(value = {"development", "production"})
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String tokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (tokenHeader != null) {
            if (tokenHeader.startsWith("Bearer ") || tokenHeader.startsWith("bearer ")) {
                // Obtener el token
                jwtToken = tokenHeader.substring(7);

                try{
                    // Obtener nombre de usuario a partir del token
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                }catch(Exception e){
                    throw new ServletException("Token expired");
                }

            }
        }


        if (username != null) {

            // Obtener toda la informacion del usuario
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // Verificamos validez del token
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                // Generamos una instancia en memoria donde este la informacion del usuario
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                userPassAuthToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardar en memoria (en el contexto de spring) el usuario que generó la petición
                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }

        //Para continuar con la petición
        filterChain.doFilter(request, response);
    }
}

