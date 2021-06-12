package br.com.mpetech.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    @Size(min = 5, message = "O nome deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe seu nome.")
    private String nome;

    @Column(name = "email", nullable = false, length = 45, unique = false)
    @NotBlank(message = "Por favor, informe seu email.")
    private String email;

    @Size(min = 3, message = "Sua senha deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe uma senha.")
    @Column(name = "senha", nullable = false)
    private String senha;
    
    @Size(min = 3, message = "Sua senha deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe uma senha.")
    @Column(name = "senha_repetida", nullable = false)
    private String senhaRepetida;

    @NotNull(message = "Por favor, informe um status para o usuário.")
    @Column(name = "status", nullable = false)
    private int status;

    @NotBlank(message = "Por favor, informe um tipo de usuário.")
    @Column(name = "roles", nullable = false, length = 45)
    private String perfil;

}
