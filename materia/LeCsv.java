package materia;

public class LeCsv {
    public static void main (String[] args) {

        CsvParser csv = new CsvParser("exemplos/grade-bcc-2019/exemplo_trabalho_TAP_Disciplinas_2019.csv");

        csv.readCsv();

        Lista<Materia> materias = csv.parseCsv();

        while (materias.hasNext()) {
            System.out.println(materias.next().toString());
        }
    }
}
