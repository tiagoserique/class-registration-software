package model;

import materia.Materia;
import java.util.Vector;
import java.util.Collections;

public class MateriaParser {

    private String materiasFile = "exemplos/grade-bcc-2019/exemplo_trabalho_TAP_Disciplinas_2019.csv";

    private static MateriaParser instancia = null;

    public static synchronized MateriaParser getInstance(){
        if (instancia == null)
            instancia = new MateriaParser();
        return instancia;
    }

    private MateriaParser() {}

    public void setMateriasFile(String materiasFile) {
        this.materiasFile = materiasFile;
    }

    public String getMateriasFile() {
        return materiasFile;
    }

    private String[] tryToSplit (String line) {
        try {
            String[] fields = line.split(";");
            return fields;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            String[] fields = {};
            return fields;
        }
    }

    private int parseInteger (String intString) {
        try {
            return Integer.parseInt(intString);
        }
        catch (NumberFormatException e) {
            return 9;
        }
    }

    public Vector<Vector<Materia>> parseMaterias() {
        
        CsvReader reader = new CsvReader(this.materiasFile);
        Vector<String> lines = reader.readCsv();

        Vector<Materia> materias = new Vector<Materia>();
        Vector<Integer> periodos = new Vector<Integer>();

        for (String line: lines) {

            String[] fields = tryToSplit(line);
            if (fields.length > 0) {

                Materia materia = new Materia();

                materia.setCodCurso(fields[0]);
                materia.setNumVersao(parseInteger(fields[1]));
                materia.setDescrEstrutura(fields[2]);
                materia.setCodDisci(fields[3]);
                materia.setNomeUnidade(fields[4]);
                materia.setNomeDisci(fields[5]);
                materia.setPeriodo(parseInteger(fields[6]));
                materia.setNumHoras(parseInteger(fields[7]));
                materia.setTipoDisci(fields[8]);
                materia.setChTotal(parseInteger(fields[9]));
                materia.setSituacao(fields[10]);
                
                materias.add(materia);

                int periodo = materia.getPeriodo();

                if (!periodos.contains(periodo))
                    periodos.add(periodo);
            }
        }
    
        Collections.sort(periodos);
        Vector<Vector<Materia>> materiasPorPeriodo = new Vector<Vector<Materia>>();

        for (int periodo: periodos) {

            Vector<Materia> materiasPeriodo = new Vector<Materia>();

            for (Materia materia: materias) {
                if (materia.getPeriodo() == periodo)
                    materiasPeriodo.add(materia);
            }

            materiasPorPeriodo.add(materiasPeriodo);
        }
        
        return materiasPorPeriodo;

    }

    public void writeMaterias(Vector<Materia> materiasPedidas) {



    }
    
}
