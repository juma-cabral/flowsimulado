package com.pedido.flowsimulado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowsimuladoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowsimuladoApplication.class, args);
	}
    /* Invoke-RestMethod -Uri "http://localhost:8080/api/pedidos" -Method Post -Body '{"nomePedido": "Meu Pedido do Salesforce"}' -ContentType "application/json"
	    SE TIVER USANDO O WINDOWS COLE ISSO NO POWERSHELL PARA VER O RESULTADO DA API*/
}
