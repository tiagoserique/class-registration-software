package model;

import java.util.Vector;

public class HistoricoParser {
    
    private String historicFile;

    public HistoricoParser () {}

    public HistoricoParser (String historicFile) {
        this.historicFile = historicFile;
    }

    public Vector<MateriaHistorico> parseHistorico() {
        
        CsvReader reader = new CsvReader(this.historicFile);
        Vector<String> lines = reader.readCsv();

        Vector<MateriaHistorico> historico = new Vector<MateriaHistorico>();

        for (String line: lines) {

            String[] fields = line.split(";");

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
        }
    
        return historico;
    }
}
