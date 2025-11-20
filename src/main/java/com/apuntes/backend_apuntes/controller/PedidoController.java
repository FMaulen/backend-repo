package com.apuntes.backend_apuntes.controller;

import com.apuntes.backend_apuntes.controller.dto.PedidoRequest;
import com.apuntes.backend_apuntes.controller.dto.PedidoResponse;
import com.apuntes.backend_apuntes.controller.dto.MaterialResponse;
import com.apuntes.backend_apuntes.model.Pedido;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.repository.PedidoRepository;
import com.apuntes.backend_apuntes.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(
            @RequestBody PedidoRequest request,
            @AuthenticationPrincipal Usuario usuarioAutenticado) {
        Pedido pedido = pedidoService.crearPedido(usuarioAutenticado, request.getMaterialIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarTodos() {
        List<PedidoResponse> response = pedidoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private PedidoResponse mapToResponse(Pedido p) {
        return PedidoResponse.builder()
                .id(p.getId())
                .fechaCreacion(p.getFechaCreacion())
                .total(p.getTotal())
                .materiales(p.getMateriales().stream().map(m -> MaterialResponse.builder()
                        .id(m.getId())
                        .titulo(m.getTitulo())
                        .materiaNombre(m.getMateria().getName())
                        .precio(m.getPrecio())
                        .build()).toList())
                .build();
    }
}
