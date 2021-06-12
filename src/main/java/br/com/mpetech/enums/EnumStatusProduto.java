package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumStatusProduto {

    INATIVO("Inativo", "Inativo"),
    ATIVO("Ativo", "Ativo");

    private String status;

    private String sigla;

    private EnumStatusProduto(String status, String sigla) {
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
