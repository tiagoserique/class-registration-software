package controller;

import java.util.*;

import view.TelaSolicitar;

import materia.Materia;
import materia.MateriaHistorico;

import model.HistoricoParser;
import model.MateriaParser;

public class TelaSolicitarController extends Controller {
    private TelaSolicitar screen;
    private HistoricoParser parserHistorico;
    private MateriaParser parserMateria;

    public void executa(Object view){
        this.screen = (TelaSolicitar) view;


        // =====================================================================

        // Mostrar matérias ofertadas neste semestre que não foram cursadas, por ordem de período.

        // ler materias que foram cursadas e que estao no historico
        Vector<Vector<MateriaHistorico>> matCursadas = parserHistorico.parseHistorico();

        // ler materias que foram ofertadas
        Vector<Vector<Materia>> matOfertadas = parserMateria.parseMaterias();

        // filtra materias que nao foram cursadas
        Vector<Materia> matNaoCursadasOfertadas = filtraMateriasNaoCursadas(matOfertadas, matCursadas);

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


    private Vector<Materia> filtraMateriasNaoCursadas(Vector<Vector<Materia>> mO, Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Materia> matNaoCursadas = new Vector<Materia>();

        for (int periodo = 0; periodo < mO.size(); periodo++){
            for (int materia = 0; materia < mO.get(periodo).size(); materia++){
                
                String codBarreira = mO.get(periodo).get(materia).getCodDisci();

                Boolean condition = jaFoiCursada(mC, codBarreira);

                if ( !condition ){ 
                    matNaoCursadas.add(mO.get(periodo).get(materia));
                }
            }
        }

        return matNaoCursadas;
    }

}
