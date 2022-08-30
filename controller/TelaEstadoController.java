package controller;

import java.util.*;

import view.TelaEstado;

import materia.Materia;
import model.MateriaHistorico;

public class TelaEstadoController extends Controller {

    private TelaEstado screen;

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

}