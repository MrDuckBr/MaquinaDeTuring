import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritaArquivo {

    FileWriter arquivo;
    BufferedWriter bw;

    public EscritaArquivo(String nomeDoArquivo) throws IOException {


    }

    public void gravarLinha(ArrayList<String> a)  {
        try (FileWriter arquivo = new FileWriter("saida.txt")) {
           for(String b: a) {
               arquivo.write(b + "\n");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
