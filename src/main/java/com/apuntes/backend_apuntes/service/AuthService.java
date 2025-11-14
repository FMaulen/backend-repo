package com.apuntes.backend_apuntes.service;

import com.apuntes.backend_apuntes.controller.dto.AuthResponse;
import com.apuntes.backend_apuntes.controller.dto.LoginRequest;
import com.apuntes.backend_apuntes.controller.dto.RegisterRequest;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Se autentica usando el nombre de usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getPassword())
        );

        // ya autenticado busca al usuario para generar el token
        // lo busca por nombreUsuario o por email
        UserDetails user = usuarioRepository.findByNombreUsuario(request.getNombreUsuario())
                .or(() -> usuarioRepository.findByEmail(request.getNombreUsuario()))
                .orElseThrow();

        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setEmail(request.getEmail());
        usuario.setRut(request.getRut());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("ROLE_USER"); // asigna rol por defecto

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.generateToken(usuario))
                .build();
    }
}