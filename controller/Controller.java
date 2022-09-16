package controller;

import java.util.Vector;

import materia.Materia;

// import materia.Materia;

import materia.MateriaHistorico;

public class Controller {
    protected String APROVADO  = "1";
    protected String REP_NOTA  = "2";
    protected String REP_FALTA = "3";
    protected String MATRICULA = "10";

    protected int CASO_A = 5;
    protected int CASO_B = 4;
    protected int CASO_C = 3;

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

    protected float calculaIra(Vector<Vector<MateriaHistorico>> historicoPorSemestre) {

        int cargaHorariaTotal = 0;
        int somaParcial = 0;

        for (Vector<MateriaHistorico> semestre: historicoPorSemestre) {
            for (MateriaHistorico materia: semestre) {
                if (!(materia.getSituacaoItem().equals(MATRICULA))) {
                    int cargaHoraria = Integer.parseInt(materia.getChTotal());
                    int notaMateria = Integer.parseInt(materia.getMediaFinal());

                    cargaHorariaTotal += cargaHoraria;
                    somaParcial += notaMateria * cargaHoraria;
                }
            }
        }

        return ((float) somaParcial / cargaHorariaTotal);
    }

    protected int calculaQuantMateriasAtuais(Vector<MateriaHistorico> materias) {
        int qtd = 0;
 
        qtd = calculaQuantidadeTipo(materias, MATRICULA);
 
        return qtd;
     }
 
    protected int calculaMateriaSugeridas(float ira, double porcentagemAprovacao) {

        if (ira >= 0.8) {
            return 6;
        } else {
            if (porcentagemAprovacao >= 0.66)
                return 5;
            else if (porcentagemAprovacao >= 0.5)
                return 4;
            else
                return 3;
        }
    }


    protected Vector<Materia> selecionaMaterias(Vector<Materia> materiasSolicitadas, int numeroMax) {

        if (materiasSolicitadas.size() <= numeroMax)
            return materiasSolicitadas;
        else {
            Vector<Materia> materiaSugeridas = new Vector<Materia>();

            for (int i=0; i<5; i++)
                materiaSugeridas.add(materiasSolicitadas.get(i));
            
            return materiaSugeridas;
        }
    }

    protected Vector<Materia> materiasIdeais(Vector<Vector<MateriaHistorico>> historicoAluno, Vector<Materia> materiasSolicitadas) {

        if (calculaIra(historicoAluno) >= 0.8) {
            return materiasSolicitadas;
        } else {
            
            Vector<MateriaHistorico> semestreAnterior = historicoAluno.lastElement();
            Double porcentagemAprovacao = calculaPorcentAprovacao(semestreAnterior);

            if (porcentagemAprovacao >= 0.66)
                return selecionaMaterias(materiasSolicitadas, CASO_A);
            else if (porcentagemAprovacao >= 0.5)
                return selecionaMaterias(materiasSolicitadas, CASO_B);
            else
                return selecionaMaterias(materiasSolicitadas, CASO_C);
        }
    }
}
