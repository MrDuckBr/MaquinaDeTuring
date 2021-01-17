public class Transicoes {

    private String estadoAtual;
    private String leituraAtual;
    private String proximoEstado;
    private String proximaEscrita;
    private String direcaoDaFita;

    public Transicoes(String estadoAtual, String leituraAtual, String proximoEstado, String proximaEscrita, String direcaoDaFita) {
        this.estadoAtual = estadoAtual;
        this.leituraAtual = leituraAtual;
        this.proximoEstado = proximoEstado;
        this.proximaEscrita = proximaEscrita;
        this.direcaoDaFita = direcaoDaFita;
    }


    public String getEstadoAtual() {
        return estadoAtual;
    }

    public String getLeituraAtual() {
        return leituraAtual;
    }

    public String getProximoEstado() {
        return proximoEstado;
    }

    public String getProximaEscrita() {
        return proximaEscrita;
    }

    public String getDirecaoDaFita() {
        return direcaoDaFita;
    }

    @Override
    public String toString() {
        return "Transicoes{" +
                "estadoAtual='" + estadoAtual + '\'' +
                ", leituraAtual='" + leituraAtual + '\'' +
                ", proximoEstado='" + proximoEstado + '\'' +
                ", proximaEscrita='" + proximaEscrita + '\'' +
                ", direcaoDaFita='" + direcaoDaFita + '\'' +
                '}';
    }
}
