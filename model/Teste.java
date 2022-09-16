package model;

import materia.Materia;
import java.util.Vector;

public class Teste {
    public static void main (String[] args) {

        MateriaParser materiaParser = MateriaParser.getInstance();

        Vector<Vector<Materia>> materias = materiaParser.parseMaterias();
        Vector<Materia> pedido = new Vector<Materia>();

        for (Materia materia: materias.firstElement())
            pedido.add(materia);

        CsvWriter<Materia> writer = new CsvWriter<Materia>("pedido.csv");
        writer.escreveArquivo(pedido);
    }
}
