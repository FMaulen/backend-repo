package com.apuntes.backend_apuntes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apuntes.backend_apuntes.model.Material;
import com.apuntes.backend_apuntes.model.Pedido;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.repository.MaterialRepository;
import com.apuntes.backend_apuntes.repository.PedidoRepository;
import com.apuntes.backend_apuntes.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MaterialRepository materialRepository;
    private final UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));
    }

    public Pedido crearPedido(Usuario usuario, List<Long> materialIds) {

        List<Material> materiales = materialRepository.findAllById(materialIds);

        if (materiales.isEmpty()) {
            throw new IllegalArgumentException("La lista de materiales no puede estar vacía");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setMateriales(materiales);
        pedido.setFechaCreacion(LocalDateTime.now());

        double total = materiales.stream()
                .mapToDouble(m -> m.getPrecio() != null ? m.getPrecio() : 0.0)
                .sum();

        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }
}
