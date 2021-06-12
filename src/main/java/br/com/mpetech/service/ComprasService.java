package br.com.mpetech.service;

import br.com.mpetech.model.Compras;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public interface ComprasService extends JpaRepository<Compras, Long>{
    
    @Query("SELECT c FROM Compras c WHERE c.emailCliente = :email")
    public List<Compras> historicoCliente(@Param("email") String email);
    
    @Query("SELECT c FROM Compras c WHERE c.id = :id")
    public List<Compras> historicoClienteId(@Param("id") Long id);
    
    @Query("SELECT n FROM Compras n ORDER BY n.dataCompra DESC")
    public Page<Compras> lista(Pageable pagina);
    
    @Query("SELECT n FROM Compras n WHERE n.numeroPedido  = :numeroPedido")
    public Page<Compras> buscaNumeroPedido(@Param("numeroPedido") Integer numeroPedido, Pageable pagina);
    
}
