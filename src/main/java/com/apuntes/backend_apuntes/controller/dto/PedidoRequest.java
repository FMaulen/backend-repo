package com.apuntes.backend_apuntes.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
    private List<Long> materialIds;
}
