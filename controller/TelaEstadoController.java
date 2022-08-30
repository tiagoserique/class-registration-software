package controller;

import java.util.*;

import view.TelaEstado;

import materia.Materia;
import model.MateriaHistorico;

public class TelaEstadoController {

    private TelaEstado screen;
    private String APROVADO  = "1";
    private String REP_NOTA  = "2";
    private String REP_FALTA = "3";
    private String MATRICULA = "10";

    //  1 - Aprovado
    //  2 - Reprovado por nota
    //  3 - Reprovado por falta/frequencia
    // 10 - Matricula

    public void executa(Object view){
        this.screen = (TelaEstado) view;

        // - Mostrar matérias cursadas por período. 
        // - Mostrar matérias que faltam cursar para barreira.
        // - Mostrar dados de aprovação do último período (% de aprovação e 
        // quantas matérias reprovou por falta).


        // =====================================================================
        // materias que ja foram cursadas de acordo com o periodo que foram 
        // cursadas pelo aluno

        // busca do model que le as MateriasHistorico
        Vector<Vector<MateriaHistorico>> matCursadas = new Vector<Vector<MateriaHistorico>>();
        this.screen.setMateriasCursadas(matCursadas);


        // =====================================================================
        // materias que faltam cursar para a barreira
        
        // armazena as materias que faltam cursar para a barreira
        Vector<Materia> matBarreira;      
        Vector<Materia> matNaoCursadasBarreira;

        // buscar da model que le todas as Materias da grade
        Vector<Vector<Materia>> materiasOfertadas = new Vector<Vector<Materia>>();

        matBarreira = filtraMateriasBarreira(materiasOfertadas);
        matNaoCursadasBarreira = filtraMateriasNaoCursadas(matBarreira, matCursadas);

        this.screen.setMateriasBarreira(matNaoCursadasBarreira);

        
        // ===================================================================== 
        // Mostrar dados de aprovação do último período (% de aprovação e 
        // quantas matérias reprovou por falta).
        
        // pega lista de materias do ultimo periodo
        Vector<MateriaHistorico> matUltimoPeriodo;

        if ( matCursadas.get(matCursadas.size() - 1).get(0).getSituacaoItem().equals(MATRICULA) )
            matUltimoPeriodo = matCursadas.get(matCursadas.size() - 2);
        else 
            matUltimoPeriodo = matCursadas.get(matCursadas.size() - 1);
        
        Double porcentAprovacao = calculaPorcentAprovacao(matUltimoPeriodo); 
        int quantidadeReprovacao = calculaQuantidadeTipo(matUltimoPeriodo, REP_FALTA); 
        
        this.screen.setPorcentAprovacao(porcentAprovacao);
        this.screen.setQuantidadeReprovacaoFalta(quantidadeReprovacao);
    }


    private Double calculaPorcentAprovacao(Vector<MateriaHistorico> materias){
        Double aprovacao = 0.0;

        aprovacao = (double) calculaQuantidadeTipo(materias, APROVADO);

        aprovacao /= (double) materias.size();

        return aprovacao;
    }


    private int calculaQuantidadeTipo(Vector<MateriaHistorico> materias, String tipo){
        int qtd = 0;

        for (int i = 0; i < materias.size(); i++){
            if ( materias.get(i).getSituacaoItem().equals(tipo) ) qtd++;
        }

        return qtd;
    }


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