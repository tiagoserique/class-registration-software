package controller;

import java.util.Vector;

import materia.Materia;

import model.MateriaHistorico;

public class Controller {
    protected String APROVADO  = "1";
    protected String REP_NOTA  = "2";
    protected String REP_FALTA = "3";
    protected String MATRICULA = "10";


    //  3 = ultimo periodo da barreira 
    // mO = materias ofertadas
    public Vector<Materia> filtraMateriasBarreira(Vector<Vector<Materia>> mO){
        Vector<Materia> matBarreira = new Vector<Materia>(); 

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < mO.get(i).size(); j++){
                matBarreira.add(mO.get(i).get(j));
            }
        }

        return matBarreira;
    }


    // pega as materias ofertadas e que ainda nao foram cursadas
    // mB = materias barreira | mC = materias cursadas
    public Vector<Materia> filtraMateriasNaoCursadas(Vector<Materia> mB, 
    Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Materia> matNaoCursadas = new Vector<Materia>();

        for (int i = 0; i < mB.size(); i++){
            String codBarreira = mB.get(i).getCodDisci();

            for (int semestre = 0; semestre < mC.size(); semestre++){
                for (int mat = 0; mat < mC.get(semestre).size(); mat++){

                    String codCursada = mC.get(semestre).get(mat).getCodAtivCurric();
                    String situacaoMateria = mC.get(semestre).get(mat).getSituacaoItem();

                    Boolean fc1 = codBarreira.equals(codCursada);
                    Boolean fc2 = situacaoMateria.equals(REP_NOTA) 
                                || situacaoMateria.equals(REP_FALTA);

                    if ( !fc1 ){ 
                        matNaoCursadas.add(mB.get(i));
                    }
                    else if ( fc2 ){
                        if ( !foiAprovadoDepois(mC, codCursada, semestre) ){
                            matNaoCursadas.add(mB.get(i));
                        }
                    }

                }
            }
        }

        return matNaoCursadas;
    }


    // checa se foi aprovado depois de ter sido reprovado
    public Boolean foiAprovadoDepois(Vector<Vector<MateriaHistorico>> mC, 
    String codigo, int i){

        for (int semestre = i + 1; semestre < mC.size(); semestre++){
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
}
