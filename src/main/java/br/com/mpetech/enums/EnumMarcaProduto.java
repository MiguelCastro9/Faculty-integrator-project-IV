package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumMarcaProduto {

    INTEL("Intel", "Intel"),
    AMD("AMD", "AMD"),
    NVIDEA("Nvidea", "Nvidea"),
    GEIL("Geil", "Geil"),
    HYPERX("HyperX", "HyperX"),
    REDDRAGON("RedDragon", "RedDragon"),
    ASUS("ASUS", "ASUS"),
    PNY("PNY", "PNY"),
    GALAX("Galax", "Galax"),
    OUTROS("Outros", "Outros");

    private String marca;

    private String sigla;

    private EnumMarcaProduto(String marca, String sigla) {
        this.marca = marca;
        this.sigla = sigla;
    }

    public String getMarca() {
        return marca;
    }

    public String getSigla() {
        return sigla;
    }

}
