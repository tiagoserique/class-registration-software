package model;

import java.util.Vector;

public class HistoricoParser {
    
    private String historicFile;

    public HistoricoParser () {}

    public HistoricoParser (String historicFile) {
        this.historicFile = historicFile;
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

    public Vector<Vector<MateriaHistorico>> parseHistorico() {
        
        CsvReader reader = new CsvReader(this.historicFile);
        Vector<String> lines = reader.readCsv();

        Vector<MateriaHistorico> historico = new Vector<MateriaHistorico>();
        Vector<String> semestres = new Vector<String>();

        for (String line: lines) {

            String[] fields = tryToSplit(line);
            if (fields.length > 0) {

                MateriaHistorico materiaHistorico = new MateriaHistorico();

                materiaHistorico.setMatrAluno(fields[0]);
                materiaHistorico.setNomePessoa(fields[1]);
                materiaHistorico.setCodCurso(fields[2]);
                materiaHistorico.setNomeCurso(fields[3]);
                materiaHistorico.setNumVersao(fields[4]);
                materiaHistorico.setAno(fields[5]);
                materiaHistorico.setMediaFinal(fields[6]);
                materiaHistorico.setSituacaoItem(fields[7]);
                materiaHistorico.setPeriodo(fields[8]);
                materiaHistorico.setSituacao(fields[9]);
                materiaHistorico.setCodAtivCurric(fields[10]);
                materiaHistorico.setNomeAtivCurric(fields[11]);
                materiaHistorico.setChTotal(fields[12]);
                materiaHistorico.setDescrEstrutura(fields[13]);
                materiaHistorico.setFrequencia(fields[14]);
                materiaHistorico.setSigla(fields[15]);
            
                historico.add(materiaHistorico);

                String periodoAno = materiaHistorico.getPeriodo() + materiaHistorico.getAno();

                if (!semestres.contains(periodoAno)) {
                    semestres.add(periodoAno);
                }
            }
        }

        Vector<Vector<MateriaHistorico>> historicoPorSemestre = new Vector<Vector<MateriaHistorico>>();

        for (String semestre: semestres) {

            Vector<MateriaHistorico> materias = new Vector<MateriaHistorico>();

            for (MateriaHistorico materia: historico) {
                
                String periodoAno = materia.getPeriodo() + materia.getAno();

                if (semestre.equals(periodoAno))
                    materias.add(materia);
            }

            historicoPorSemestre.add(materias);
        }
    
        return historicoPorSemestre;
    }
}
