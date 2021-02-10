/**
 * Classe Principal que inicializa a maquina de Turing
 *
 *
 */
import java.io.IOException;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        String arquivoEntrada, arquivoSaida;
        System.out.print("Digite o nome do arquivo de entrada ( com extensao .txt): ");
        arquivoEntrada = entrada.next();
        System.out.print("Digite o nome do arquivo de saida ( com extensao .txt): ");
        arquivoSaida = entrada.next();
        Maquina maquina = new Maquina();
        maquina.inicializacaoMaquina(arquivoEntrada, arquivoSaida);


    }
}
