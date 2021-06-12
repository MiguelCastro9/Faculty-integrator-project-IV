package br.com.mpetech.model;

import br.com.mpetech.enums.EnumClienteUF;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "enderecos_extras")
public class EnderecosExtras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;
    
    @Column(name = "logradouro", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe o logradouro de endereço.")
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @NotNull(message = "Por favor, informe o número de endereço.")
    private Integer numero;

    @Column(name = "localidade", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe a localidade.")
    private String localidade;

    @Column(name = "uf", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe a UF de estado.")
    private EnumClienteUF uf;

    @Column(name = "complemento", length = 255)
    private String complemento;

    @Column(name = "cep", nullable = false, length = 9)
    @NotBlank(message = "Por favor, informe o CEP de endereço.")
    private String cep; 

}
