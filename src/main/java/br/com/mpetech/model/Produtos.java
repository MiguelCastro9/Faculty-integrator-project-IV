package br.com.mpetech.model;

import br.com.mpetech.enums.EnumMarcaProduto;
import br.com.mpetech.enums.EnumStatusEstoque;
import br.com.mpetech.enums.EnumStatusProduto;
import br.com.mpetech.enums.EnumTipoProduto;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Data
@Entity
@Table(name = "produtos")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tipo_produto", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, selecione o tipo de produto.")
    private EnumTipoProduto enumTipoProduto;

    @Column(name = "status_estoque", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, selecione o status do estoque.")
    private EnumStatusEstoque enumStatusEstoque;

    @Column(name = "status_produto", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, selecione o status do produto.")
    private EnumStatusProduto enumStatusProduto;

    @Column(name = "imagem_primaria")
    private String imagemPrimaria;

    @Column(name = "imagem_secundaria")
    private String imagemSecundaria;

    @Column(name = "imagem_terciaria")
    private String imagemTerciaria;

    @Column(name = "marca_produto", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, selecione a marca do produto.")
    private EnumMarcaProduto enumMarcaProduto;

    @Column(name = "nome_produto", nullable = false, length = 255)
    @Size(min = 3, message = "O nome do produto deve conter no mínimo {min} caracteres.")
    @NotBlank(message = "Por favor, informe o nome do produto.")
    private String nomeProduto;

    @Column(name = "estrelas_produto", nullable = false)
    @NotNull(message = "Por favor, informe a quantidade de estrelas do produto.")
    private Integer estrelaProduto;

    @Column(name = "valor_produto", nullable = false)
    @NotNull(message = "Por favor, informe um valor a ser pago ao produto.")
    private BigDecimal valorProduto;

    @Column(name = "quantidade_produto", nullable = false)
    @NotNull(message = "Por favor, informe a quantidade do produto.")
    private Integer quantidadeProduto;

    @Column(name = "descricao_produto", nullable = false, length = 255)
    @NotBlank(message = "Por favor, informe a descrição do produto.")
    private String descricaoProduto;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produtos other = (Produtos) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
    

}
