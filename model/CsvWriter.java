package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Vector;

public class CsvWriter<T> {

    public CsvWriter () {}

    public void escreveArquivo(Vector<T> objetos) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pedido.csv"));

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
