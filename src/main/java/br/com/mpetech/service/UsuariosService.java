package br.com.mpetech.service;

import br.com.mpetech.model.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public interface UsuariosService extends JpaRepository<Usuarios, Long>{
    
    @Query("SELECT n FROM Usuarios n WHERE n.nome LIKE %:nome% ORDER BY n.id DESC")
    public Page<Usuarios> buscaNomeUsuario(@Param("nome") String nome, Pageable pagina);
    
    @Query("SELECT n FROM Usuarios n ORDER BY n.id DESC")
    public Page<Usuarios> listaDESC(Pageable pagina);
    
}
