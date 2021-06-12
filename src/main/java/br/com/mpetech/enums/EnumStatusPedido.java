package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumStatusPedido {

    AGUARDANDO_PAGAMENTO("Aguardando pagamento", "Aguardando pagamento", 1),
    PAGAMENTO_APROVADO("Pagamento aprovado", "Pagamento aprovado", 2),
    PAGAMENTO_REJEITADO("Pagamento rejeitado", "pagamento rejeitado", 3),
    AGUARDANDO_RETIRADA("Aguardando retirada", "Aguardando retirada", 4),
    EM_TRANSITO("Em transito", "Em transito", 5),
    ENTREGUE("Entregue", "Entregue", 6);

    private final String status;

    private final String sigla;
    
    private final Integer codigo;

    private EnumStatusPedido(String status, String sigla, Integer codigo) {
        this.status = status;
        this.sigla = sigla;
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public String getSigla() {
        return sigla;
    }

    public Integer getCodigo() {
        return codigo;
    }
    
    

}
