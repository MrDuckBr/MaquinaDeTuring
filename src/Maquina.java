/**
 * Classe que contém todo o core do programa
 *
 * @author Walisson Mendes Ferreira
 */
import java.io.IOException;
import java.util.ArrayList;

public class Maquina {
    LeituraArquivo leituraArquivo;
    EscritaArquivo escritaArquivo;
    ArrayList<Transicoes> transicoes;
    ArrayList<String> fita, escrita;
    String arquivoSaida;
    int posicaoDaCabecaDeLeitura = 0;

    boolean verificador;
    Transicoes estado;


    public Maquina() {
        leituraArquivo = new LeituraArquivo();
        escrita = new ArrayList<>();
    }

    /**
     * Metodo que inicializa a maquina carregando as informacoes do arquivo
     * @param arquivoEntrada recebe o nome do arquivo a ler as informacoes da maquina
     * @param arquivoSaida recebe o nome do arquivo que irá grava a saida da maquina
     * @throws IOException erro gerado caso nao consiga ler o arquivo
     */
    public void inicializacaoMaquina(String arquivoEntrada, String arquivoSaida) throws IOException {
        leituraArquivo.leArquivo(arquivoEntrada);
        escritaArquivo = new EscritaArquivo();
        this.arquivoSaida = arquivoSaida;
        transicoes = leituraArquivo.getTransicoes();
        fita = leituraArquivo.getFitaInicial();
        estado = estadoInicial();
        verificador = false;
        verificaEstadoFinal(false);

    }

    /**
     * Metodo que encontra a proxima transicao
     *
     * @param estadoAtual recebe o primeiro estado
     * @param leituraDaFita recebe a leitura da fita na posicao
     * @return o estado encontrado
     */
    public Transicoes encontraTransicao(String estadoAtual, String leituraDaFita) {
        for (Transicoes t : transicoes) {
            if (t.getEstadoAtual().equals(estadoAtual) && t.getLeituraAtual().equals(leituraDaFita)) {
                return t;
            }
        }
        verificaEstadoFinal(true);
        return null;
    }

    /**
     * Este metodo atribui a variavel estado , todas as informacoes do estado inicial
     * @return o estado inicial
     */
    public Transicoes estadoInicial() {
        String valorInicialFita = leituraArquivo.getFitaInicial().get(0);
        estado = encontraTransicao(leituraArquivo.getEstadoInicialdaFita(), valorInicialFita);
        return estado;
    }

    /**
     * Metodo que atribui o novo estada que a maquina esta
     * @param proximoEstado informa o proximo estado a ser lido
     * @param leitura informa oque foi lido na fita naquele estado
     */
    public void proximoEstado(String proximoEstado, String leitura) {
        estado = encontraTransicao(proximoEstado, leitura);
        if (estado == null) {
            escritaArquivo.gravarLinha(escrita, arquivoSaida);
            // System.out.println("Estado ao qual deseja ir nao existe " + proximoEstado);
        }
    }

    /**
     * Metodo que le o estado e escreve a proxima palavra na fita
     * @param index palavra a ser escrita na fita
     * @param posicao posicao a qual ira escrever na fita
     * @param estado estado atual que esta sendo lido para obter os parametros
     */
    public void escreveFita(String index, int posicao, String estado) {
        fita.set(posicao, index);
        System.out.println(ajustaFita(posicao, estado));
        escrita.add(ajustaFita(posicao, estado));
    }

    /**
     * Metodo que verifica se a maquina chegou a um estado final aceito, no caso ultimo estado e na primeira posicao de leitura
     * @param ver recebe o parametro para verificar se e o fim da maquina
     */
    public void verificaEstadoFinal(boolean ver) {
        verificador = ver;
        while (!verificador) {
            coreMaquina();
        }
        if (estado != null) {
            escreveFita(fita.get(posicaoDaCabecaDeLeitura), posicaoDaCabecaDeLeitura, estado.getEstadoAtual());
        }
    }

    /**
     * Metodo que controla a cabeca de leitura, as trancicoes e a escrita na fita de forma geral
     * @return caso seja o fim da maquina retorna true, caso contrario retorna false
     */

    public boolean coreMaquina() {
        if (estado.getEstadoAtual().equals(estado.getProximoEstado())
                && posicaoDaCabecaDeLeitura == 0
                && estado.getDirecaoDaFita().equals("L")) {
            return true;
        }

        escreveFita(estado.getProximaEscrita(), posicaoDaCabecaDeLeitura, estado.getEstadoAtual());

        //System.out.println(posicaoDaCabecaDeLeitura);
        if (estado.getDirecaoDaFita().equals("R")) {
            posicaoDaCabecaDeLeitura++;
        } else if (estado.getDirecaoDaFita().equals("L")) {
            posicaoDaCabecaDeLeitura--;
        }
        proximoEstado(estado.getProximoEstado(), fita.get(posicaoDaCabecaDeLeitura));

        // if(estado == null) return true;
        return false;
    }

    /**
     *
     * @param index posicao da cabeca de leitura a qual sera escrita o estado
     * @param estado estado atual de leitura
     * @return retorna a linha daquela transicao formatada
     */
    public String ajustaFita(int index, String estado) {
        String saida = "";
        for (int i = 0; i < fita.size(); i++) {
            if (i == index) {
                saida = saida + "{" + estado + "}";
                saida = saida + fita.get(i);
            } else {
                saida = saida + " " + fita.get(i);
            }

        }
        return saida;
    }


}
