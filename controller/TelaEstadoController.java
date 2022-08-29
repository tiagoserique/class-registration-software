package controller;

import java.util.*;

import view.TelaEstado;

import materia.Materia;
import materia.MateriaHistorico;

public class TelaEstadoController {

    private TelaEstado screen;

    public void executa(Object view){
        this.screen = (TelaEstado) view;


        // ===================================================================== 
        // Mostrar matérias cursadas por período.
        //      -> montar matriz com os periodos x materias
        //          -> por materias cursadas com cor verde e nao cursadas com cor vermelha?

        Vector<Vector<MateriaHistorico>> materiasCursadasPorPeriodo = new Vector<Vector<MateriaHistorico>>();
        this.screen.setMateriasCursadasPorPeriodo(materiasCursadasPorPeriodo);

        // ===================================================================== 
        // Mostrar matérias que faltam cursar para barreira.
        //      -> montar lista de materias pre barreiras que ainda nao foram cursadas

        Vector<Materia> materiasBarreira = new Vector<Materia>();
        this.screen.setMateriasBarreira(materiasBarreira);

        
        
        // ===================================================================== 
        // Mostrar dados de aprovação do último período (% de aprovação e quantas matérias reprovou por falta).
        //      -> montar lista de materias cursadas no ultimo periodo
        //          -> calcular % de aprovacao 
        //          -> calcular quantidade de reprovacao por falta 
        
        Vector<MateriaHistorico> materiasCursadasUltimoPeriodo = new Vector<MateriaHistorico>();
        
        Double porcentAprovacao = calculaPorcentAprovacao(materiasCursadasUltimoPeriodo); 
        int quantidadeReprovacao = calculaQuantidadeReprovacao(materiasCursadasUltimoPeriodo); 
        
        this.screen.setPorcentAprovacao(porcentAprovacao);
        this.screen.setQuantidadeReprovacao(quantidadeReprovacao);


        // retornar para a view
    }


    private Double calculaPorcentAprovacao(Vector<MateriaHistorico> materias){
        Double aprovacao = 0.0;

        for (int i = 0; i < materias.size(); i++){
            if ( materias.get(i).getSituacaoItem().equals("1") ) aprovacao++;
        }

        aprovacao /= (double) materias.size();

        return aprovacao;
    }


    private int calculaQuantidadeReprovacao(Vector<MateriaHistorico> materias){
        int reprovacao = 0;

        for (int i = 0; i < materias.size(); i++){
            if ( materias.get(i).getSituacaoItem().equals("3") ) reprovacao++;
        }

        return reprovacao;
    }
}