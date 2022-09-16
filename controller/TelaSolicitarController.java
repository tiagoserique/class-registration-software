package controller;

import java.util.*;

import view.TelaSolicitar;
import view.TelaSub;
import view.Tela;

import materia.Materia;
import model.CsvWriter;

public class TelaSolicitarController extends Controller implements TelaSub{
    private TelaSolicitar screen;

    public TelaSolicitarController(){
        this.screen = TelaSolicitar.getInstance();
        this.screen.subscribe(this);
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

            // this.parserMateria.writeMaterias(materiasSolicitadas, path);
            CsvWriter<Materia> csvWriter = new CsvWriter<Materia>(path);
            csvWriter.escreveArquivo(materiasSolicitadas);
        }
    }
}
