package br.com.mpetech.service;

import br.com.mpetech.model.Clientes;
import br.com.mpetech.model.EnderecosExtras;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public interface EnderecosExtrasService extends JpaRepository<EnderecosExtras, Long> {

    @Query("SELECT e FROM EnderecosExtras e WHERE e.cliente = :id")
    public List<EnderecosExtras> listarEnderecoExtraCliente(@Param("id") Clientes id);

}