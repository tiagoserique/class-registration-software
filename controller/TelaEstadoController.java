package controller;

import java.util.*;

import view.TelaEstado;

import materia.Materia;
import materia.MateriaHistorico;

public class TelaEstadoController extends Controller {
    private TelaEstado screen;


    public void executa(Object view){
        this.screen = (TelaEstado) view;

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


    //  3 = ultimo periodo da barreira 
    // mO = materias ofertadas
    private Vector<Materia> filtraMateriasBarreira(Vector<Vector<Materia>> mO){
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
    private Vector<Materia> filtraMateriasNaoCursadas(Vector<Materia> mB, 
    Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Materia> matNaoCursadas = new Vector<Materia>();

        for (int materia = 0; materia < mB.size(); materia++){
            String codBarreira = mB.get(materia).getCodDisci();

            Boolean condition = jaFoiCursada(mC, codBarreira);

            if ( !condition ){ 
                matNaoCursadas.add(mB.get(materia));
            }

        }

        return matNaoCursadas;
    }

}