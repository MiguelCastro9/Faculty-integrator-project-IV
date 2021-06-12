package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumClienteUF {

        RO("Rondônia", "RO"),
	AC("Acre", "AC"),
	AM("Amazonas", "AM"),
	RR("Roraima", "RR"),
	PA("Pará", "PA"),
	AP("Amapá", "AP"),
	TO("Tocantins", "TO"),
	MA("Maranhão", "MA"),
	PI("Piauí", "PI"),
	CE("Ceará", "CE"),
	RN("Rio Grande do Norte", "RN"),
	PB("Paraíba", "PB"),
	PE("Pernambuco", "PE"),
	AL("Alagoas", "AL"),
	SE("Sergipe", "SE"),
	BA("Bahia", "BA"),
	MG("Minas Gerais", "MG"),
	ES("Espírito Santo", "ES"),
	RJ("Rio de Janeiro", "RJ"),
	SP("São Paulo", "SP"),
	PR("Paraná", "PR"),
	SC("Santa Catarina", "SC"),
	RS("Rio Grande do Sul", "RS"),
	MS("Mato Grosso do Sul", "MS"),
	MT("Mato Grosso", "MT"),
	GO("Goiás", "GO"),
	DF("Distrito Federal", "DF");

    private String ufs;

    private String sigla;

    private EnumClienteUF(String ufs, String sigla) {
        this.ufs = ufs;
        this.sigla = sigla;
    }

    public String getUfs() {
        return ufs;
    }

    public String getSigla() {
        return sigla;
    }
}
