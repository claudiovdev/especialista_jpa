package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger{


    @Column(name = "pedido_id")
    private Integer pedidoId;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @Column(name = "codigo_de_barras")
    private String codigoDeBarras;


}