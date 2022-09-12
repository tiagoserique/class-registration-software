package model;

import materia.Materia;
import java.util.Vector;

public class Teste {
    public static void main (String[] args) {

        MateriaParser materiaParser = new MateriaParser("exemplos/grade-bcc-2019/exemplo_trabalho_TAP_Disciplinas_2019.csv");

        Vector<Vector<Materia>> materias = materiaParser.parseMaterias();

        for (Vector<Materia> periodo: materias) {
            System.out.println("Periodo " + periodo.firstElement().getPeriodo());
            for (Materia materia: periodo)
                System.out.println(materia.getCodDisci() + " - " + materia.getNomeDisci());
        }
    }
}
