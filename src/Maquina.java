import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Maquina {
    LeituraArquivo leituraArquivo;
    EscritaArquivo escritaArquivo;
    ArrayList<Transicoes> transicoes;
    ArrayList<String> fita, escrita;
    String  leituraDaFita;
    int posicaoDaCabecaDeLeitura = 0;

    boolean verificador;
    Transicoes estado;


    public Maquina() throws IOException {
        leituraArquivo = new LeituraArquivo();
        escritaArquivo = new EscritaArquivo("saida");
        escrita = new ArrayList<>();
    }

    public void inicializacaoMaquina()throws FileNotFoundException {
        leituraArquivo.leArquivo();

        transicoes = leituraArquivo.getTransicoes();
        fita = leituraArquivo.getFitaInicial();
        estado = estadoInicial();
        verificador = false;
        verificaEstadoFinal(false);

    }


    public Transicoes encontraTransicao(String estadoAtual , String leituraDaFita) {
        for (Transicoes t : transicoes) {
            if (t.getEstadoAtual().equals(estadoAtual) && t.getLeituraAtual().equals(leituraDaFita)) {
                    return t;
            }
        }
        verificaEstadoFinal(true);
        return null;
    }

    public Transicoes estadoInicial(){
        String valorInicialFita = leituraArquivo.getFitaInicial().get(0);
        estado = encontraTransicao(leituraArquivo.getEstadoInicialdaFita(), valorInicialFita);
            return estado;
    }

    public void proximoEstado(String proximoEstado, String leitura){
        estado = encontraTransicao(proximoEstado, leitura);
        if(estado == null){
           escritaArquivo.gravarLinha(escrita);
            // System.out.println("Estado ao qual deseja ir nao existe " + proximoEstado);
        }
    }

    public void escreveFita(String index, int posicao, String estado)  {
        fita.set(posicao, index);
        System.out.println(ajustaFita(posicao,estado));
        escrita.add(ajustaFita(posicao,estado));
    }

    public void verificaEstadoFinal(boolean ver){
        verificador = ver;
        while (!verificador){
                coreMaquina();
            }

            if(estado != null){
                escreveFita( fita.get(posicaoDaCabecaDeLeitura),posicaoDaCabecaDeLeitura, estado.getEstadoAtual());
            }
    }


    public boolean coreMaquina() {
        if (estado.getEstadoAtual().equals(estado.getProximoEstado())
                && posicaoDaCabecaDeLeitura == 0
                && estado.getDirecaoDaFita().equals("L")) {
            return true;
        }

        escreveFita(estado.getProximaEscrita(), posicaoDaCabecaDeLeitura,estado.getEstadoAtual());

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




    public String ajustaFita(int index,String estado) {
        String saida = "";
        for (int i = 0; i < fita.size(); i++) {
            if(i == index){
                saida = saida + "{" + estado + "}";
                saida = saida  + fita.get(i);
            }else{
                saida = saida + " " + fita.get(i);
            }

        }
        return saida;
    }












}
