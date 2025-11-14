package com.apuntes.backend_apuntes.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombreUsuario;
    private String email;
    private String rut;
    private String nombre;
    private String apellido;
    private String password;
}
