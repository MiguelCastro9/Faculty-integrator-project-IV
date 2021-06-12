package br.com.mpetech.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "item_compra")
public class ItemCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private Compras compra;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Produtos produto;
    
    @Column(name = "quantidade_produto")
    private int quantidade;
    

}
