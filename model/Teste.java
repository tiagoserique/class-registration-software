package model;

import java.util.Vector;

public class Teste {
    public static void main (String[] args) {

        HistoricoParser historicoAluno = new HistoricoParser("exemplos/historico/exemplo_trabalho_TAP_historico.csv");

        Vector<MateriaHistorico> materiasHistorico = historicoAluno.parseHistorico();

        for (MateriaHistorico materia: materiasHistorico) {
            System.out.println(materia.getCodAtivCurric() + " " + materia.getSituacao());
        }
    }
}
