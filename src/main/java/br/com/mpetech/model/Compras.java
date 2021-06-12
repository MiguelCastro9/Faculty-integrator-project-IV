package br.com.mpetech.model;

import br.com.mpetech.enums.EnumStatusPedido;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "compras")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Compras implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "endereco_entrega", length = 255, nullable = false)
    private String enderecoEntrega;
    
    @Column(name = "forma_pagamento", length = 45, nullable = false)
    @NotBlank(message = "Por favor, selecione uma forma de pagamento.")
    private String formaPagamento;

    @Column(name = "valor_total", nullable = false)
    private String valorTotal;
    
    @Column(name = "nome_cartao", length = 45)
    private String nomeCartao;
    
    @Column(name = "numero_cartao")
    private Long numeroCartao;
    
    @Column(name = "codigo_cartao")
    private Integer codigoCartao;
    
    @Column(name = "parcela_cartao", length = 45)
    private String parcelasCartao;
    
    @Column(name = "vencimento_cartao", length = 45)  
    private String vencimentoCartao;
    
    @Column(name = "data_compra", length = 45, nullable = false)
    private String dataCompra;
    
    @Column(name = "status_pedido", length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStatusPedido statusPedido;
    
    @Column(name = "numero_pedido", nullable = false)
    private Integer numeroPedido;
    
    @Column(name = "quantidade_pedido", nullable = false)
    private Integer quantidadePedido;
    
    @Column(name = "produto_pedido", nullable = false, length = 255)
    private String produtoPedido;
    
    @Column(name = "quantidade_produto_pedido", nullable = false, length = 255)
    private String quantidadeProdutoPedido;
    
    @Column(name = "frete_compra", length = 45, nullable = false)
    @NotNull(message = "Por favor, selecione um frete.")
    private BigDecimal freteCompra;
    
    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;
    
}
