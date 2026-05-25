package com.pedido.flowsimulado.controller;

import com.pedido.flowsimulado.modelo.Pedido;
import com.pedido.flowsimulado.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }
}