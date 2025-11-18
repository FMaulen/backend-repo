package com.apuntes.backend_apuntes.controller.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoResponse {

    private Long id;
    private LocalDateTime fechaCreacion;
    private Double total;

    private List<MaterialResponse> materiales;
}
