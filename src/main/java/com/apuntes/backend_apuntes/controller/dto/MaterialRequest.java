package com.apuntes.backend_apuntes.controller.dto;

import lombok.Data;

@Data

public class MaterialRequest {

    private String titulo;
    private String descripcion;
    private Double precio;
    private Long materiaId;

}
