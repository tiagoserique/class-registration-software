package model;

import materia.Lista;
import materia.Materia;
import materia.MateriaHistorico;

public class LeCsv {
    public static void main (String[] args) {

        // Materia
        CsvParser csv = new CsvParser("exemplos/grade-bcc-2019/exemplo_trabalho_TAP_Disciplinas_2019.csv");

        csv.readCsv();

        Lista<Materia> materias = csv.parseCsv();

        while (materias.hasNext()) {
            System.out.println(materias.next().toString());
        }

        // Historico - adicionei isso
        CsvParser csv2 = new CsvParser("exemplos/historico/exemplo_trabalho_TAP_historico.csv");

        csv2.readCsv();

        Lista<MateriaHistorico> historico = csv2.parseCsv();

        while (historico.hasNext()) {
            System.out.println(historico.next().toString());
        }
    }
}
