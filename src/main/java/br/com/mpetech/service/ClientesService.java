package br.com.mpetech.service;

import br.com.mpetech.model.Clientes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public interface ClientesService extends JpaRepository<Clientes, Long>{

    @Query("SELECT c FROM Clientes c WHERE c.email = :email")
    public List<Clientes> exibePerfil(@Param("email") String email);
    
    @Query("SELECT c FROM Clientes c WHERE c.email = :email")
    public Clientes buscaIdCliente(@Param("email") String email);
}
