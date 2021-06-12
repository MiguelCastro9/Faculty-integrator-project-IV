package br.com.mpetech.model;

import br.com.mpetech.enums.EnumClienteUF;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "clientes")
public class Clientes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    @OneToMany(mappedBy = "cliente")
    private List<EnderecosExtras> enderecosExtra;
    
    @Column(name = "email", nullable = false, length = 45, unique = false)
    @Email(message = "Por favor, informe um E-mail válido.")
    @NotBlank(message = "Por favor, informe seu E-mail.")
    private String email;
    
    @Column(name = "nome", nullable = false, length = 20)
    @Size(min = 3, message = "Seu nome deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe o seu nome.")
    private String nome;
    
    @Column(name = "sobrenome", nullable = false, length = 20)
    @Size(min = 3, message = "Seu sobrenome deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe o seu sobrenome.")
    private String sobrenome;
    
    @Size(min = 3, message = "Sua senha deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe uma senha.")
    @Column(name = "senha", nullable = false)
    private String senha;
    
    @Size(min = 3, message = "Sua senha deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe uma senha.")
    @Column(name = "senha_repetida", nullable = false)
    private String senhaRepetida;
    
    @Column(name = "cpf", nullable = false, length = 14, unique = false)
    @CPF(message = "CPF inválido.")
    @NotBlank(message = "Por favor, informe seu CPF.")
    private String cpf;
    
    @Column(name = "imagem_perfil")
    private String imagemPerfil;
    
    @Column(name = "logradouro_cliente", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe o logradouro de endereço.")
    private String logradouroCliente;
    
    @Column(name = "numero_cliente", nullable = false)
    @NotNull(message = "Por favor, informe o seu número de endereço.")
    private Integer numeroCliente;
    
    @Column(name = "localidade_cliente", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe sua localidade.")
    private String localidadeCliente;
    
    @Column(name = "uf_cliente", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe sua UF de estado.")
    private EnumClienteUF ufCliente;
    
    @Column(name = "complemento_cliente", length = 255)
    private String complementoCliente;
    
    @Column(name = "cep_cliente", nullable = false, length = 9)
    @NotBlank(message = "Por favor, informe seu CEP de endereço.")
    private String cepCliente;
    
    @Column(name = "logradouro_faturamento", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe o logradouro de endereço do faturamento.")
    private String logradouroFaturamento;
    
    @Column(name = "numero_faturamento", nullable = false)
    @NotNull(message = "Por favor, informe o número de endereço do faturamento.")
    private Integer numeroFaturamento;
    
    @Column(name = "localidade_faturamento", nullable = false, length = 45)
    @NotBlank(message = "Por favor, informe a localidade do faturamento.")
    private String localidadeFaturamento;
    
    @Column(name = "uf_faturamento", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe a UF de estado do faturamento.")
    private EnumClienteUF ufFaturamento;
    
    @Column(name = "complemento_faturamento", length = 255)
    private String complementoFaturamento;
    
    @Column(name = "cep_faturamento", nullable = false, length = 9)
    @NotBlank(message = "Por favor, informe o CEP de endereço do faturamento.")
    private String cepFaturamento;
    
    @Column(name = "status", nullable = false)
    private int status;
    
    @Column(name = "roles", nullable = false, length = 45)
    private String perfil;
    
}
