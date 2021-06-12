package br.com.mpetech.enums;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public enum EnumTipoProduto {

    PC_GAMERS("Computadores Gamers", "Computadores Gamers"),
    GABINETES("Gabinetes", "Gabinetes"),
    MONITORES("Monitores", "Monitores"),
    PROCESSADORES("Processadores", "Processadores"),
    PLACAS_MAES("Placa Mãe", "Placa Mãe"),
    PLACAS_VIDEO("Placa de Vídeo", "Placa de Vídeo"),
    MEMORIAS("Memórias", "Memórias"),
    HARD_DISK("Hard Disk", "Hard Disk"),
    FONTES("Fontes", "Fontes"),
    TECLADOS("Teclados", "Teclados"),
    MOUSES("Mouses", "Mouses"),
    CADEIRAS_GAMERS("Cadeiras Gamers", "Cadeiras Gamers"),
    WEBCAM("Webcams", "Webcams"),
    IMPRESSORAS("Impressoras", "Impressoras"),
    ACESSORIOS("Acessórios", "Acessórios");

    private String tipo;

    private String sigla;

    private EnumTipoProduto(String tipo, String sigla) {
        this.tipo = tipo;
        this.sigla = sigla;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSigla() {
        return sigla;
    }

}
