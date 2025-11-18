package com.apuntes.backend_apuntes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apuntes.backend_apuntes.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
