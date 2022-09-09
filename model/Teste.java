package model;

import java.util.Vector;

public class Teste {
    public static void main (String[] args) {

        HistoricoParser historicoAluno = new HistoricoParser("exemplos/historico/exemplo_trabalho_TAP_historico.csv");

        Vector<Vector<MateriaHistorico>> materiasHistorico = historicoAluno.parseHistorico();

        int cargaHorariaTotal = 0;
        int somaTotal = 0;

        for (Vector<MateriaHistorico> semestre: materiasHistorico) {

            for (MateriaHistorico materia: semestre) {
                
                if (!(materia.getSituacaoItem().equals("10"))) {
                    int cargaHoraria = Integer.parseInt(materia.getChTotal());
                    int notaMateria = Integer.parseInt(materia.getMediaFinal());

                    cargaHorariaTotal += cargaHoraria;
                    somaTotal += notaMateria * cargaHoraria;
                }
            }
        }

        System.out.println(somaTotal);
        System.out.println(cargaHorariaTotal);
        System.out.println("IRA: " + ((float) somaTotal / (cargaHorariaTotal * 100)));
    }
}
