# Salesforce Flow Architecture Simulated in Java (Spring Boot)

Este repositório apresenta uma prova de conceito (PoC) que traduz a lógica de automação declarativa do **Salesforce (Record-Triggered Flows)** para uma arquitetura robusta de Back-end em **Java** utilizando **Spring Boot** e **Spring Data JPA**.

O objetivo principal é demonstrar como regras de negócio nativas de plataformas de CRM podem ser replicadas, escaladas e integradas no ecossistema Java.

---

##  O Cenário de Negócio 

No ecossistema Salesforce, uma das automações mais comuns é o disparo de ações após a criação ou atualização de um registro. Este projeto replica exatamente o seguinte requisito de negócio:

> **Gatilho (Trigger):** Quando um novo registro do objeto customizado `Pedido__c` for inserido no sistema com sucesso...
> **Ação Automática (Immediate Action):** O sistema deve gerar instantaneamente um registro filho no objeto `Entrega__c`, vinculando-os via relacionamento de busca (*Lookup/One-to-One*), definindo o status inicial como `PENDENTE` e atribuindo um ID de entregador padrão.



---

## Mapeamento de Conceitos: Salesforce vs Java/Spring

Para profissionais de tecnologia, a tabela abaixo ilustra como os componentes declarativos da plataforma Salesforce foram codificados nesta API Java:

| Componente Salesforce | Equivalente em Java (Spring Boot) | Função no Projeto |
| :--- | :--- | :--- |
| **Custom Object (`Pedido__c`)** | Classe `Pedido` com `@Entity` | Mapeia a tabela de pedidos no banco de dados. |
| **Custom Object (`Entrega__c`)** | Classe `Entrega` com `@Entity` | Mapeia a tabela de entregas e gerencia a chave estrangeira. |
| **Picklist (`Status__c`)** | `StatusEntrega` (`Enum`) | Restringe os status estritamente a: `PENDENTE`, `EM_ANDAMENTO`, `ENTREGUE`. |
| **Lookup Relationship (1:1)** | Anotação `@OneToOne` | Cria a integridade referencial entre as tabelas. |
| **Record-Triggered Flow (After Insert)** | Classe `PedidoService` com `@Transactional` | Executa a inteligência de criar a entrega logo após salvar o pedido. |
| **Salesforce API / Apex REST** | Classe `PedidoController` com `@RestController` | Expõe a rota HTTP para que sistemas externos criem os pedidos. |

---

## Arquitetura do Código (Camadas)

A aplicação segue o padrão de arquitetura em camadas do Spring Boot, garantindo que as regras de automação fiquem isoladas e protegidas de interferências externas:

* **`com.pedido.flowsimulado.modelo`:** Contém as entidades de dados e regras de validação física (Enums).
* **`com.pedido.flowsimulado.repositorio`:** Interfaces que herdam `JpaRepository`, responsáveis pelas operações automáticas de banco de dados (equivalente às operações DML do Apex como `insert` e `update`).
* **`com.pedido.flowsimulado.service`:** Onde reside o nosso **Flow**. Essa camada centraliza a regra de negócio e garante que o pedido e a entrega sejam salvos juntos ou falhem juntos (Atomicidade/Transacionalidade).
* **`com.pedido.flowsimulado.controller`:** A porta de entrada (API Endpoint) que intercepta as requisições externas em formato JSON.

---

## Como Executar e Testar o Fluxo

### 1. Inicialização do Servidor
Abra o projeto na sua IDE (ex: IntelliJ IDEA) e execute a classe `FlowsimuladoApplication.java`. O Spring Boot iniciará um servidor embutido Apache Tomcat na porta **8080** e criará o banco de dados temporário H2 em memória automaticamente.

### 2. Simulação do Disparo 

Cole exatamente esse caminho no PowerShell "Invoke-RestMethod -Uri "http://localhost:8080/api/pedidos" -Method Post -Body '{"nomePedido": "Meu Pedido do Salesforce"}' -ContentType "application/json""
