import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritaArquivo {

    FileWriter arquivo;
    BufferedWriter bw;
    public EscritaArquivo(String nomeDoArquivo) throws IOException {
        File file = new File(nomeDoArquivo+".txt");
        arquivo = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(arquivo);
    }



    public void gravaLinha(String fita) throws IOException {
       System.out.println(fita);
       String teste = "t";
        bw.write(teste);
        bw.newLine();

    }
}
