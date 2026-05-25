package com.pedido.flowsimulado.modelo;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private Long entregadorId;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    public Entrega() {}

    public Entrega(Pedido pedido) {
        this.pedido = pedido;
        this.status = StatusEntrega.PENDENTE; // Começa automaticamente como PENDENTE!
        this.entregadorId = 1L;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Mudamos os tipos aqui para StatusEntrega
    public StatusEntrega getStatus() { return status; }
    public void setStatus(StatusEntrega status) { this.status = status; }

    public Long getEntregadorId() { return entregadorId; }
    public void setEntregadorId(Long entregadorId) { this.entregadorId = entregadorId; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
}