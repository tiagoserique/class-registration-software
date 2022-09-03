package model;

import materia.Lista;
import materia.Materia;
import materia.MateriaHistorico;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvParser {
    
    private String filename;
    private Lista<String> lines;

    public CsvParser () {}

    public CsvParser (String filename) {
        this.filename = filename;
        this.lines = new Lista<String>();
    }

    public void readCsv() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String nextLine = reader.readLine();
            
            while ((nextLine = reader.readLine()) != null) {
                lines.insere(nextLine);
            }

            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private int intConversion(String intString) {

        try {
            return Integer.parseInt(intString);
        } 
        catch (Exception e) {
            return 0;
        }
    }

    public Lista<Materia> parseCsv() {
        
        String [] fields;
        Lista<Materia> materias = new Lista<Materia>();

        while (lines.hasNext()) {
            
            Materia newMateria = new Materia();
        
            fields = lines.next().split(";");

            newMateria.setCodCurso(fields[0]);
            newMateria.setNumVersao(intConversion(fields[1]));
            newMateria.setDescrEstrutura(fields[2]);
            newMateria.setCodDisci(fields[3]);
            newMateria.setNomeUnidade(fields[4]);
            newMateria.setNomeDisci(fields[5]);
            newMateria.setPeriodo(intConversion(fields[6]));
            newMateria.setNumHoras(intConversion(fields[7]));
            newMateria.setTipoDisci(fields[8]);
            newMateria.setChTotal(intConversion(fields[9]));
            newMateria.setSituacao(fields[10]);

            materias.insere(newMateria);
        }

        return materias;

    }

    // Historico - adicionei isso
    public Lista<MateriaHistorico> parseCsvHistorico() {

        String [] fields;
        Lista<MateriaHistorico> historico = new Lista<MateriaHistorico>();

        while (lines.hasNext()) {

            MateriaHistorico newHistorico = new MateriaHistorico();

            fields = lines.next().split(";");

            newHistorico.setMatrAluno(fields[0]);
            newHistorico.setNomePessoa(fields[1]);
            newHistorico.setCodCurso(fields[2]);
            newHistorico.setNomeCurso(fields[3]);
            newHistorico.setNumVersao(fields[4]);
            newHistorico.setAno(fields[5]);
            newHistorico.setMediaFinal(fields[6]);
            newHistorico.setSituacaoItem(fields[7]);
            newHistorico.setPeriodo(fields[8]);
            newHistorico.setSituacao(fields[9]);
            newHistorico.setCodAtivCurric(fields[10]);
            newHistorico.setNomeAtivCurric(fields[11]);
            newHistorico.setChTotal(fields[12]);
            newHistorico.setDescrEstrutura(fields[13]);
            newHistorico.setFrequencia(fields[14]);
            newHistorico.setSigla(fields[15]);

            historico.insere(newHistorico);
        }

        return historico;
    }

}
