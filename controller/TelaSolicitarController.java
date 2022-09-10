package controller;

import java.util.*;

import view.TelaSolicitar;

import materia.Materia;
import materia.MateriaHistorico;

public class TelaSolicitarController extends Controller {
    private TelaSolicitar screen;


    public void executa(Object view){
        this.screen = (TelaSolicitar) view;


        // =====================================================================

        // Mostrar matérias ofertadas neste semestre que não foram cursadas, por ordem de período.

        // ler materias que foram cursadas e que estao no historico
        Vector<Vector<MateriaHistorico>> matCursadas = new Vector<Vector<MateriaHistorico>>();

        // ler materias que foram ofertadas
        Vector<Vector<Materia>> matOfertadas = new Vector<Vector<Materia>>();

        // filtra materias que nao foram cursadas
        Vector<Vector<Materia>> matNaoCursadasOfertadas = filtraMateriasNaoCursadas(matOfertadas, matCursadas);

        // mostra materias que nao foram cursadas
        this.screen.setMateriasNaoCursadasOfertadas(matNaoCursadasOfertadas);

        // =====================================================================
        
        // Permitir selecionar matérias que quer cursar com ordem de prioridade.
        // Permitir enviar matérias selecionadas para fazer pedido.

        // Vector<Materia> matSelecionadas = getMateriasSelecionadas();

        // checa requisitos de quebra de barreira pra ver se tudo ok

        // se tudo ok, salva pedido

        // senao, mostra mensagem de erro
    }


    private Vector<Vector<Materia>> filtraMateriasNaoCursadas(Vector<Vector<Materia>> mO, Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Vector<Materia>> matNaoCursadas = new Vector<Vector<Materia>>();

        for (int periodo = 0; periodo < mO.size(); periodo++){
            for (int materia = 0; materia < mO.get(periodo).size(); materia++){
                
                String codBarreira = mO.get(periodo).get(materia).getCodDisci();

                Boolean condition = jaFoiCursada(mC, codBarreira);

                if ( !condition ){ 
                    matNaoCursadas.add(mO.get(materia));
                }
            }
        }

        return matNaoCursadas;
    }

}
