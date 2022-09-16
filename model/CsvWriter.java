package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Vector;

public class CsvWriter<T> {
    private String path;

    public CsvWriter (String path) {
        this.path = path;
    }

    public void escreveArquivo(Vector<T> objetos) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));

            for (T objeto: objetos) {
                writer.write(objeto.toString());
                writer.newLine();
            }

            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
