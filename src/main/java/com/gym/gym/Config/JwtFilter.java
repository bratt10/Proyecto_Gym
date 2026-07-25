package com.gym.gym.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        // 1. Saca el header Authorization de la petición
        String authHeader = request.getHeader("Authorization");

        // 2. Si no viene token o no empieza con "Bearer ", deja pasar sin autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrae el token quitando "Bearer "
        String token = authHeader.substring(7);

        // 4. Valida el token
        if (jwtUtil.validarToken(token)) {
            String correo = jwtUtil.extraerCorreo(token);

            // 5. Le dice a Spring Security "este usuario está autenticado"
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 6. Continúa con la petición
        filterChain.doFilter(request, response);
    }
}