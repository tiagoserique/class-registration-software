package controller;

import java.util.Vector;

// import materia.Materia;

import materia.MateriaHistorico;

public class Controller {
    protected String APROVADO  = "1";
    protected String REP_NOTA  = "2";
    protected String REP_FALTA = "3";
    protected String MATRICULA = "10";


    // checa se a materia ja foi cursada e o aluno aprovado
    // mC = materias cursadas
    public Boolean jaFoiCursada(Vector<Vector<MateriaHistorico>> mC, String codigo){
        for (int semestre = 0; semestre < mC.size(); semestre++){
            for (int matCursada = 0; matCursada < mC.get(semestre).size(); matCursada++){

                String codCursada = mC.get(semestre).get(matCursada).getCodAtivCurric();
                String situacaoMateria = mC.get(semestre).get(matCursada).getSituacaoItem();

                Boolean cond1 = codigo.equals(codCursada);
                Boolean cond2 = situacaoMateria.equals(APROVADO);

                if ( cond1 && cond2 ) return true;

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

    protected float calculaIra(Vector<MateriaHistorico> materias) {

        int cargaHorariaTotal = 0;
        int somaParcial = 0;

        for (MateriaHistorico materia: materias) {
            
            if (!(materia.getSituacaoItem().equals(MATRICULA))) {
                int cargaHoraria = Integer.parseInt(materia.getChTotal());
                int notaMateria = Integer.parseInt(materia.getMediaFinal());

                cargaHorariaTotal += cargaHoraria;
                somaParcial += notaMateria * cargaHoraria;
            }
        }

        return ((float) somaParcial / cargaHorariaTotal);
    }
}
