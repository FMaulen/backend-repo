package com.apuntes.backend_apuntes.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MaterialResponse {

    private Long id;
    private String titulo;
    private String descripcion;
    private Double precio;

    private String materiaNombre;
    private String materiaCodigo;

    private String usuarioNombre;
}
