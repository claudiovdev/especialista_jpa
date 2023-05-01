package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    @Column(name = "data_conclusao_pedido")
    private LocalDateTime dataConclusaoPedido;
    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal total;
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

}