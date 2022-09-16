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

        
        // =====================================================================
        // Permitir enviar matérias selecionadas para fazer pedido.
        if ( view.getPedido() == "save" ){
            String path = this.screen.getExportPath();

            Vector<Materia> materiasSolicitadas = this.screen.getMateriasNaoCursadasSolicitadas();
            System.out.println(path);
            System.out.println(materiasSolicitadas);
            
            // TODO: descomentar quando o writeMaterias estiver completo
            // this.parserMateria.writeMaterias(materiasSolicitadas, path);
        }
    }
}
