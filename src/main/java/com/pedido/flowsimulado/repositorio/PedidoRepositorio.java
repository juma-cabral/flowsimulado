package com.pedido.flowsimulado.repositorio;

import com.pedido.flowsimulado.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}