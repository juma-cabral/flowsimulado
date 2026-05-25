package com.pedido.flowsimulado.service;

import com.pedido.flowsimulado.modelo.Entrega;
import com.pedido.flowsimulado.modelo.Pedido;
import com.pedido.flowsimulado.repositorio.EntregaRepositorio;
import com.pedido.flowsimulado.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private EntregaRepositorio entregaRepositorio;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        // 1. Salva o pedido (O gatilho do seu Flow: "Um registro é criado")
        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        // 2. Ação automática: Cria e vincula a entrega ao ID do pedido salvo
        Entrega novaEntrega = new Entrega(pedidoSalvo);
        entregaRepositorio.save(novaEntrega);

        // Vincula de volta no objeto para conseguirmos ver o resultado no teste
        pedidoSalvo.setEntrega(novaEntrega);

        return pedidoSalvo;
    }
}