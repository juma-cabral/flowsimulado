package com.pedido.flowsimulado.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePedido;
    private LocalDateTime dataCriacao;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Entrega entrega;

    public Pedido() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomePedido() { return nomePedido; }
    public void setNomePedido(String nomePedido) { this.nomePedido = nomePedido; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Entrega getEntrega() { return entrega; }
    public void setEntrega(Entrega entrega) { this.entrega = entrega; }
}