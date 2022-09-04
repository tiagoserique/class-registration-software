package model;

import java.util.Vector;

public class Teste {
    public static void main (String[] args) {

        HistoricoParser historicoAluno = new HistoricoParser("exemplos/historico/exemplo_trabalho_TAP_historico.csv");

        Vector<MateriaHistorico> materiasHistorico = historicoAluno.parseHistorico();

        System.out.println(materiasHistorico.firstElement().getNomePessoa());

        for (MateriaHistorico materia: materiasHistorico) {
            System.out.println(materia.getCodAtivCurric() + " - " + materia.getNomeAtivCurri() + " -> " + materia.getSituacao());
        }

        int cargaHorariaTotal = 0;
        int somaParcial = 0;

        for (MateriaHistorico materia: materiasHistorico) {
            
            if (!(materia.getSituacaoItem().equals("10"))) {

                int cargaHoraria = Integer.parseInt(materia.getChTotal());
                int notaMateria = Integer.parseInt(materia.getMediaFinal());

                cargaHorariaTotal += cargaHoraria;
                somaParcial += notaMateria * cargaHoraria;
            }
        }

        float ira = ((float) somaParcial / (cargaHorariaTotal * 100));
    
        System.out.println("IRA: " + ira);
    }
}
