
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que le o arquivo
 */
public class LeituraArquivo {
    ArrayList<String> estados, alfabetoEntrada, alfabetoFita, fitaInicial;
    ArrayList<Transicoes> transicoes;
    String estadoInicialdaFita;


    public LeituraArquivo() {
        //Leitura dos estados e alfabetos;
        estados = new ArrayList<>();
        alfabetoEntrada = new ArrayList<>();
        alfabetoFita = new ArrayList<>();
        transicoes = new ArrayList<>();
        fitaInicial = new ArrayList<>();

    }

    /**
     * Metodo que quebra o arquivo e atribui aos metodos certos
     *
     * @param arquivo recebe o arquivo por parametro
     * @throws FileNotFoundException erro gerado caso o arquivo nao existir
     */
    public void leArquivo(String arquivo) throws FileNotFoundException {
        FileReader reader = new FileReader(arquivo);
        Scanner entrada = new Scanner(reader); // Leu o arquivo.
        String linha = entrada.nextLine();
        boolean estado = false, alfabetoEntrada = false, alfabetoFita = false;
        int chaves = 0;

        while (linha != null) {
            linha = entrada.nextLine();
            if (!estado) {
                estado = true;
                leituraEstados(linha);
            } else if (!alfabetoEntrada) {
                alfabetoEntrada = true;
                leituraAlfabetoEntrada(linha);
            } else if (!alfabetoFita) {
                alfabetoFita = true;
                leituraAlfabetoFita(linha);
            } else if (chaves != 2) {
                if (linha.equals("{")) chaves++;
                else if (linha.equals("}")) chaves = 2;
                else {
                    leituraTransicoes(linha);
                    //chaves = 2;
                }
            } else if (estadoInicialdaFita == null) {
                estadoInicialFita(linha);
            } else {
                linha = entrada.nextLine();
                fitaInicial(linha);
                // System.out.println(linha + " Nova linha ");
                linha = null;
            }
        }

    }

    /**
     * Metodo que define o numero de estados da maquina
     *
     * @param linha recebe uma string com os estados
     */
    public void leituraEstados(String linha) {
        char[] arrayLinha = linha.toCharArray();
        for (int i = 0; i < linha.length(); i++) {
            if (arrayLinha[i] == 'q') {
                estados.add((String.valueOf(arrayLinha[i]) + arrayLinha[i + 1]));
            }
        }
    }

    /**
     * Metodo que define o alfabeto da maquina
     *
     * @param linha recebe uma string com o alfabeto da maquina
     */
    public void leituraAlfabetoEntrada(String linha) {
        char[] arrayAlfabeto = linha.toCharArray();
        for (int i = 1; i < linha.length(); i++) {
            if (arrayAlfabeto[i] != '}' && arrayAlfabeto[i] != ',') {
                alfabetoEntrada.add(String.valueOf(arrayAlfabeto[i]));
            }
        }
    }

    /**
     * Metodo que define quais os alfabetos pertencem a fita
     *
     * @param linha recebe uma string com o alfabeto da fita
     */
    public void leituraAlfabetoFita(String linha) {
        char[] arrayAlfabeto = linha.toCharArray();
        for (int i = 1; i < linha.length(); i++) {
            if (arrayAlfabeto[i] != '}' && arrayAlfabeto[i] != ',') {
                alfabetoFita.add(String.valueOf(arrayAlfabeto[i]));
            }
        }
    }

    /**
     * Metodo que define as transicoes e adiciona ao ArrayList do tipo Transicao
     *
     * @param linha recebe uma string com a transicao
     */
    public void leituraTransicoes(String linha) {
        // System.out.println(linha);
        char[] arrayTransicoes = linha.toCharArray();
        transicoes.add(new Transicoes( // Tratar se o estado atual e o proximo esta na lista de estados;
                (String.valueOf(arrayTransicoes[1]) + arrayTransicoes[2]),
                (String.valueOf(arrayTransicoes[5])),
                (String.valueOf(arrayTransicoes[12]) + arrayTransicoes[13]),
                (String.valueOf(arrayTransicoes[16])),
                (String.valueOf(arrayTransicoes[19]))));
    }

    /**
     * Metodo que define o estado inicial da fita
     *
     * @param linha recebe uma string com o estado inicial
     */
    public void estadoInicialFita(String linha) {
        char[] arrayEstadoInicialDaFita = linha.toCharArray();
        estadoInicialdaFita = String.valueOf(arrayEstadoInicialDaFita[1]) + arrayEstadoInicialDaFita[2];
    }

    /**
     * Metodo que le o arquivo e atribui ao array de fitaInicial da maquina
     *
     * @param linha recebe uma string que e a fita inicial
     */
    public void fitaInicial(String linha) {
        char[] arrayFitaInicial = linha.toCharArray();
        for (int i = 0; i < linha.length(); i++) {
            fitaInicial.add(String.valueOf(arrayFitaInicial[i]));
        }
    }


    /**
     * Get que retorna a lista de transicoes
     *
     * @return lista de transicoes
     */
    public ArrayList<Transicoes> getTransicoes() {
        return transicoes;
    }

    /**
     * get que retorna o estado que a fita se inicia
     *
     * @return estado em que a maquina inicia
     */
    public String getEstadoInicialdaFita() {
        return estadoInicialdaFita;
    }

    /**
     * get que retorna a fita inicial da maquina
     *
     * @return a fita inicial
     */
    public ArrayList<String> getFitaInicial() {
        return fitaInicial;
    }
}






