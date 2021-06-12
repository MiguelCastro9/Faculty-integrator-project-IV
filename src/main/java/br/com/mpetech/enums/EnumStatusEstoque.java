package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumStatusEstoque {

    DISPONIVEL("Disponível", "Disponível"),
    ESGOTADO("Esgotado", "Esgotado");

    private String status;

    private String sigla;

    private EnumStatusEstoque(String status, String sigla) {
        this.status = status;
        this.sigla = sigla;
    }

    public String getStatus() {
        return status;
    }

    public String getSigla() {
        return sigla;
    }

}
