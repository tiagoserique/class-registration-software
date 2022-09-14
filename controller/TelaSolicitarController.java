package controller;

import java.util.*;

import view.TelaSolicitar;
import view.TelaSub;
import view.Tela;

import materia.Materia;
import materia.MateriaHistorico;

import model.HistoricoParser;
import model.MateriaParser;

public class TelaSolicitarController extends Controller implements TelaSub{
    private TelaSolicitar screen;
    private HistoricoParser parserHistorico;
    private MateriaParser parserMateria;

    public TelaSolicitarController(){
        this.screen = TelaSolicitar.getInstance();
        this.screen.subscribe(this);
        this.parserHistorico = HistoricoParser.getInstance();
        this.parserMateria = MateriaParser.getInstance();
    }

    public void update(Tela view){
        this.screen = (TelaSolicitar) view;

        if ( view.getPedido() == "export" ){
            // =====================================================================
            
            // Permitir selecionar matérias que quer cursar com ordem de prioridade.
            // Permitir enviar matérias selecionadas para fazer pedido.

            // Vector<Materia> matSelecionadas = getMateriasSelecionadas();

            // checa requisitos de quebra de barreira pra ver se tudo ok

            // se tudo ok, salva pedido

            // senao, mostra mensagem de erro
        }
    }
}
