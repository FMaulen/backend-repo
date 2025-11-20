package com.apuntes.backend_apuntes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apuntes.backend_apuntes.controller.dto.MaterialResponse;
import com.apuntes.backend_apuntes.controller.dto.PedidoRequest;
import com.apuntes.backend_apuntes.controller.dto.PedidoResponse;
import com.apuntes.backend_apuntes.model.Pedido;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.service.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(
            @RequestBody PedidoRequest request,
            @AuthenticationPrincipal Usuario usuarioAutenticado) {

        Pedido pedido = pedidoService.crearPedido(usuarioAutenticado, request.getMaterialIds());

        PedidoResponse response = PedidoResponse.builder()
                .id(pedido.getId())
                .fechaCreacion(pedido.getFechaCreacion())
                .total(pedido.getTotal())
                .materiales(
                        pedido.getMateriales().stream()
                                .map(m -> MaterialResponse.builder()
                                        .id(m.getId())
                                        .titulo(m.getTitulo())
                                        .descripcion(m.getDescripcion())
                                        .precio(m.getPrecio())
                                        .materiaNombre(m.getMateria().getName())
                                        .materiaCodigo(m.getMateria().getCode())
                                        .usuarioNombre(m.getUsuario().getNombreUsuario())
                                        .build()
                                ).toList()
                )
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
