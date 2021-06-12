package br.com.mpetech.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

    public void add(CarrinhoItem item) {
        itens.put(item, getQuantidade(item) + 1);
    }
    
    public void remove(CarrinhoItem item) {
        itens.put(item, getQuantidade(item) - 1);      
    }

    public Collection<CarrinhoItem> getItens() {
        return itens.keySet();
    }

    public Integer getQuantidade(CarrinhoItem item) {
        if (!itens.containsKey(item)) {
            itens.put(item, 0);
        }
        return itens.get(item);
    }

    public int getQuantidade() {
        return itens.values().stream()
                .reduce(0, (proximo, acumulador) -> proximo + acumulador);
    }

    public BigDecimal getTotal(CarrinhoItem item) {
        return item.getTotal(getQuantidade(item));
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (CarrinhoItem item : itens.keySet()) {
            total = total.add(getTotal(item));
        }

        return total;
    }

    public void remover(Integer produtoId) {
        Long produtoIdL = produtoId.longValue();
        Produtos produto = new Produtos();
        produto.setId(produtoIdL);
        itens.remove(new CarrinhoItem(produto));
    }
    
    public void removerTodos(){
        
        itens.clear();
    }

}
