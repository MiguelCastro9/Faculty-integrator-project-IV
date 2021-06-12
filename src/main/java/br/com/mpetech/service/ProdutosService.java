package br.com.mpetech.service;

import br.com.mpetech.model.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public interface ProdutosService extends JpaRepository<Produtos, Long> {

    
    @Query("SELECT n FROM Produtos n WHERE n.nomeProduto LIKE %:nome%")
    public Page<Produtos> buscaNomeProduto(@Param("nome") String nome, Pageable pagina);
    
}
