package controller;

import java.util.Vector;

// import materia.Materia;

import model.MateriaHistorico;

public class Controller {
    protected String APROVADO  = "1";
    protected String REP_NOTA  = "2";
    protected String REP_FALTA = "3";
    protected String MATRICULA = "10";


    // checa se foi aprovado depois de ter sido reprovado
    // mC = materias cursadas
    // semReprov = semestre da reprovacao
    protected Boolean foiAprovadoDepois(Vector<Vector<MateriaHistorico>> mC, 
    String codigo, int semReprov){

        for (int semestre = semReprov + 1; semestre < mC.size(); semestre++){
            for (int materia = 0; materia < mC.get(semestre).size(); materia++){
                
                String codCursada = mC.get(semestre).get(materia).getCodAtivCurric();
                String situacaoMateria = mC.get(semestre).get(materia).getSituacaoItem();

                Boolean fc1 = codCursada.equals(codigo);
                Boolean fc2 = situacaoMateria.equals(APROVADO);

                if ( fc1 && fc2 ) return true;

            }
        }

        return false;
    }


    protected Double calculaPorcentAprovacao(Vector<MateriaHistorico> materias){
        Double aprovacao = 0.0;

        aprovacao = (double) calculaQuantidadeTipo(materias, APROVADO);

        aprovacao /= (double) materias.size();

        return aprovacao;
    }


    protected int calculaQuantidadeTipo(Vector<MateriaHistorico> materias, String tipo){
        int qtd = 0;

        for (int i = 0; i < materias.size(); i++){
            if ( materias.get(i).getSituacaoItem().equals(tipo) ) qtd++;
        }

        return qtd;
    }
}
