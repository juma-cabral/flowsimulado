package com.pedido.flowsimulado.repositorio;

import com.pedido.flowsimulado.modelo.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositorio extends JpaRepository<Entrega, Long> {
}