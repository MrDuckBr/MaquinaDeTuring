/**
 * Classe que escreve o arquivo de saida do programa
 *
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritaArquivo {


    /**
     * Metodo que grava em um arquivo a saida da maquina
     * @param saidas Array com todas as saidas da maquina
     * @param arquivoSaida string com o nome do arquivo de saida
     */
    public void gravarLinha(ArrayList<String> saidas , String arquivoSaida)  {
        try (FileWriter arquivo = new FileWriter(arquivoSaida)) {
           for(String b: saidas) {
               arquivo.write(b + "\n");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
