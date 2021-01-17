import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LeituraArquivo {
    ArrayList<String> estados,alfabetoEntrada,alfabetoFita,fitaInicial;
    ArrayList<Transicoes>transicoes;
    String estadoInicialdaFita;




    public LeituraArquivo(){
        //Leitura dos estados e alfabetos;
        estados = new ArrayList<>();
        alfabetoEntrada = new ArrayList<>();
        alfabetoFita = new ArrayList<>();
        transicoes= new ArrayList<>();
        fitaInicial = new ArrayList<>();

    }



    public void leArquivo() throws FileNotFoundException {
        FileReader reader = new FileReader("Entrada.txt");
        Scanner entrada = new Scanner(reader); // Leu o arquivo.
        String linha = entrada.nextLine();
        boolean estado = false, alfabetoEntrada = false,alfabetoFita = false;
        int chaves = 0;

        while(linha != null){
            linha = entrada.nextLine();
            if(!estado){
                estado = true;
                leituraEstados(linha);
            }else if(!alfabetoEntrada){
                alfabetoEntrada = true;
                leituraAlfabetoEntrada(linha);
            }else if(!alfabetoFita){
                alfabetoFita = true;
               leituraAlfabetoFita(linha);
            }else if(chaves != 2) {
                if (linha.equals("{")) chaves++;
                else if (linha.equals("}")) chaves = 2;
                else {
                    leituraTransicoes(linha);
                    //chaves = 2;
                }
            }else if(estadoInicialdaFita == null){
                estadoInicialFita(linha);
            }else{
                linha = entrada.nextLine();
                fitaInicial(linha);
               // System.out.println(linha + " Nova linha ");
                linha = null;
            }
        }

    }



    public void leituraEstados(String linha){
      char[] arrayLinha = linha.toCharArray();
      for (int i =0; i < linha.length() ; i++){
         if(arrayLinha[i] == 'q'){
             estados.add((String.valueOf(arrayLinha[i]) + arrayLinha[i+1]));
         }
      }
      //System.out.println(estados);
    }

    public void leituraAlfabetoEntrada(String linha){
        char[] arrayAlfabeto = linha.toCharArray();
        for(int i = 1; i < linha.length(); i++){
            if(arrayAlfabeto[i] != '}' && arrayAlfabeto[i] != ',' ){
                alfabetoEntrada.add(String.valueOf(arrayAlfabeto[i]));
            }
        }
        //System.out.println(alfabetoEntrada);
    }

    public void leituraAlfabetoFita(String linha){
        char[] arrayAlfabeto = linha.toCharArray();
        for(int i = 1; i < linha.length(); i++){
            if(arrayAlfabeto[i] != '}' && arrayAlfabeto[i] != ',' ){
                alfabetoFita.add(String.valueOf(arrayAlfabeto[i]));
            }
        }
       // System.out.println(alfabetoFita);
    }

    public void leituraTransicoes(String linha){
       // System.out.println(linha);
        char[] arrayTransicoes = linha.toCharArray();
        transicoes.add(new Transicoes( // Tratar se o estado atual e o proximo esta na lista de estados;
                (String.valueOf(arrayTransicoes[1]) + arrayTransicoes[2]),
                (String.valueOf(arrayTransicoes[5])),
                (String.valueOf(arrayTransicoes[12])+arrayTransicoes[13]),
                (String.valueOf(arrayTransicoes[16])),
                (String.valueOf(arrayTransicoes[19]))));
    }

    public void estadoInicialFita(String linha){
        char[] arrayEstadoInicialDaFita = linha.toCharArray();
        estadoInicialdaFita = String.valueOf(arrayEstadoInicialDaFita[1]) + arrayEstadoInicialDaFita[2];
    }

    public void fitaInicial(String linha){
        char[] arrayFitaInicial = linha.toCharArray();
        for (int i = 0 ; i < linha.length();i++){
            fitaInicial.add(String.valueOf(arrayFitaInicial[i]));
        }
    }


    public ArrayList<String> getEstados() {
        return estados;
    }

    public ArrayList<String> getAlfabetoEntrada() { return alfabetoEntrada; }

    public ArrayList<String> getAlfabetoFita() { return alfabetoFita; }

    public ArrayList<Transicoes> getTransicoes() {
        return transicoes;
    }

    public String getEstadoInicialdaFita() {
        return estadoInicialdaFita;
    }

    public ArrayList<String> getFitaInicial() {
        return fitaInicial;
    }
}






