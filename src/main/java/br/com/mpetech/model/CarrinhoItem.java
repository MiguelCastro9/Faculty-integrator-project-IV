package br.com.mpetech.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public class CarrinhoItem {

    private Produtos produto;

    public CarrinhoItem(Produtos produto) {
        this.produto = produto;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return produto.getValorProduto();
    }

    BigDecimal getTotal(Integer quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.produto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarrinhoItem other = (CarrinhoItem) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

}
